/*
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015-2016 Bertrand Martel
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.lybank.pcapdecoder.structure.options.impl;


import com.lybank.model.constant.LinkLayerError;
import com.lybank.model.constant.PacketBoundState;
import com.lybank.model.constant.PacketHashType;
import com.lybank.model.constant.PacketReceptionType;
import com.lybank.pcapdecoder.structure.options.abstr.OptionsAbstr;
import com.lybank.pcapdecoder.structure.options.inter.IOptions;
import com.lybank.pcapdecoder.structure.options.object.OptionEnhancedPacketHeaderObject;
import com.lybank.pcapdecoder.utils.UtilFunctions;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Implementation for Options in Enhanced Packet Section
 *
 * @author Bertrand Martel
 */
public class OptionsEnhancedHeader extends OptionsAbstr {

    /**
     * common option object used to retrieve all options information
     */
    private OptionEnhancedPacketHeaderObject commonObject = null;

    /**
     * decode enhance packet section options
     *
     * @param optionCode    option code
     * @param data          option value
     * @param isBigEndian   endianness
     * @param currentOption common object
     */
    public OptionsEnhancedHeader(int optionCode, byte[] data, boolean isBigEndian, IOptions currentOption) {
        super(optionCode, data, isBigEndian, currentOption);
        this.commonObject = (OptionEnhancedPacketHeaderObject) currentOption;

        // decode directly (no need to be called each time)
        decode();
    }

    /**
     * decode options
     * TODO : put comment parsing in abstract
     */
    public void decode() {
        try {
            switch (optionCode) {
                case 1:
                    if (!isBigEndian)
                        commonObject.setComment(new String(UtilFunctions.convertLeToBe(data), "UTF-8"));
                    else
                        commonObject.setComment(new String(data, "UTF-8"));
                    break;
                case 2:

                    if (!isBigEndian) {
                        parseLinkLayerInfo(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 4)));
                    } else
                        parseLinkLayerInfo(Arrays.copyOfRange(data, 0, 4));
                    break;
                case 3:
                    byte[] commonTemp = null;

                    if (!isBigEndian)
                        commonTemp = UtilFunctions.convertLeToBe(data);
                    else
                        commonTemp = data;

                    if (commonTemp[0] == 0)
                        commonObject.setPacketHashType(PacketHashType.TWOS_COMPLEMENT);
                    else if (commonTemp[1] == 1)
                        commonObject.setPacketHashType(PacketHashType.XOR);
                    else if (commonTemp[1] == 2)
                        commonObject.setPacketHashType(PacketHashType.CRC32);
                    else if (commonTemp[1] == 3)
                        commonObject.setPacketHashType(PacketHashType.MD5);
                    else if (commonTemp[1] == 4)
                        commonObject.setPacketHashType(PacketHashType.SHA1);
                    else
                        commonObject.setPacketHashType(PacketHashType.UNKNOWN);

                    commonObject.setPacketHashBigEndian(Arrays.copyOfRange(commonTemp, 1, commonTemp.length));
                    break;
                case 4:

                    if (!isBigEndian)
                        commonObject.setDropPacketCount(UtilFunctions.convertByteArrayToInt(UtilFunctions
                                .convertLeToBe(Arrays.copyOfRange(data, 0, 8))));
                    else
                        commonObject.setDropPacketCount((UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data,
                                0, 8))));
                    break;
                default:
                    break;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse link layer from big endian format
     *
     * @param data
     */
    private void parseLinkLayerInfo(byte[] data) {
        //BIT 0  to 1   INBOUND/OUTBOUND      => 00 = information not available, 01 = inbound, 10 = outbound
        //BIT 2  to 4   RECEPTION TYPE        => 000 = not specified, 001 = unicast, 010 = multicast, 011 =
        // broadcast, 100 = promiscuous
        //BIT 5  to 8   FCS length, in bytes  => (0000 if this information is not available)
        //BIT 16 to 31	link-layer-dependent errors (Bit 31 = symbol error, Bit 30 = preamble error, Bit 29 = Start
        // Frame Delimiter error, Bit 28 = unaligned frame error, Bit 27 = wrong Inter Frame Gap error, Bit 26 =
        // packet too short error, Bit 25 = packet too long error, Bit 24 = CRC error
        byte bound = (byte) (data[data.length - 1] & 0b00000011);

        if (bound == 1)
            commonObject.setPacketBound(PacketBoundState.INBOUND);
        else if (bound == 2)
            commonObject.setPacketBound(PacketBoundState.OUTBOUND);
        else
            commonObject.setPacketBound(PacketBoundState.UNKNOWN);

        byte receptionType = (byte) (data[data.length - 1] & 0b00011100);

        if (receptionType == 0b00000100)
            commonObject.setPacketReceptionType(PacketReceptionType.UNICAST);
        else if (receptionType == 0b00001000)
            commonObject.setPacketReceptionType(PacketReceptionType.MULTICAST);
        else if (receptionType == 0b00001100)
            commonObject.setPacketReceptionType(PacketReceptionType.BROADCAST);
        else if (receptionType == 0b00010000)
            commonObject.setPacketReceptionType(PacketReceptionType.PROMISCUOUS);
        else
            commonObject.setPacketReceptionType(PacketReceptionType.UNKNOWN);

        byte fcsLength = (byte) (data[data.length - 1] & 0b11100000);

        if (fcsLength != 0) {
            commonObject.setFrameCheckSumLength(fcsLength & 0xFF);
        }

        byte errorDetectionByte = data[0];

        byte symbolError = (byte) (errorDetectionByte & 0b10000000);
        byte preambleError = (byte) (errorDetectionByte & 0b01000000);
        byte startFrameDelimiterError = (byte) (errorDetectionByte & 0b00100000);
        byte unalignedFrameError = (byte) (errorDetectionByte & 0b00010000);
        byte wrongInterFrameGapError = (byte) (errorDetectionByte & 0b00001000);
        byte packetTooShortError = (byte) (errorDetectionByte & 0b00000100);
        byte packetTooLongError = (byte) (errorDetectionByte & 0b00000010);
        byte crcError = (byte) (errorDetectionByte & 0b00000001);

        if (symbolError == 0b10000000)
            commonObject.addLinkLayerError(LinkLayerError.SYMBOL_ERRROR);
        if (preambleError == 0b01000000)
            commonObject.addLinkLayerError(LinkLayerError.PREAMBLE_ERRO);
        if (startFrameDelimiterError == 0b00100000)
            commonObject.addLinkLayerError(LinkLayerError.START_FRAME_DELIMITER_ERROR);
        if (unalignedFrameError == 0b00010000)
            commonObject.addLinkLayerError(LinkLayerError.UNALIGNED_FRAME_ERROR);
        if (wrongInterFrameGapError == 0b00001000)
            commonObject.addLinkLayerError(LinkLayerError.WRONG_INTER_FRAME_GAP_ERROR);
        if (packetTooShortError == 0b00000100)
            commonObject.addLinkLayerError(LinkLayerError.PACKET_TOO_SHORT_ERROR);
        if (packetTooLongError == 0b00000010)
            commonObject.addLinkLayerError(LinkLayerError.PACKET_TOO_LONG_ERROR);
        if (crcError == 0b00000001)
            commonObject.addLinkLayerError(LinkLayerError.CRC_ERROR);
    }
}
