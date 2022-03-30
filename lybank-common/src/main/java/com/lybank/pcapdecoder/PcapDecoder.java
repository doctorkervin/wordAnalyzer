package com.lybank.pcapdecoder;/*
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

import com.lybank.model.constant.HeaderBlocks;
import com.lybank.model.constant.MagicNumber;
import com.lybank.pcapdecoder.structure.BlockTypes;
import com.lybank.pcapdecoder.structure.PcapNgStructureParser;
import com.lybank.pcapdecoder.structure.types.IPcapngType;
import com.lybank.pcapdecoder.utils.DecodeException;
import com.lybank.pcapdecoder.utils.DecoderStatus;
import com.lybank.pcapdecoder.utils.Endianess;
import com.lybank.pcapdecoder.utils.UtilFunctions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * PCAP NG decoder
 *
 * @author Bertrand Martel
 */
public class PcapDecoder {

    /**
     * data to parse
     */
    private byte[] data = null;

    private boolean isBigEndian = true;

    private ArrayList<IPcapngType> pcapSectionList = new ArrayList<IPcapngType>();

    /**
     * build Pcap Decoder with a new data to parse (from Pcap Ng file)
     *
     * @param data
     */
    public PcapDecoder(byte[] data) {
        this.data = data;
    }

    /**
     * Build Pcap Decoder from an absolute file path
     *
     * @param inputFilePath absolute file path
     */
    public PcapDecoder(String inputFilePath) {
        if (inputFilePath != null) {
            try {
                this.data = Files.readAllBytes(Paths.get(inputFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Detect endianess with magic number in section header block : will be  0x1A2B3C4D in big endian and  0x4D3C2B1A
     * for little endian
     *
     * @param magicNumber
     * @return
     */
    private byte detectEndianness(byte[] magicNumber) {
        if (UtilFunctions.compare32Bytes(MagicNumber.MAGIC_NUMBER_BIG_ENDIAN, magicNumber, isBigEndian)) {
            return Endianess.BIG_ENDIAN;
        } else if (UtilFunctions.compare32Bytes(MagicNumber.MAGIC_NUMBER_LITTLE_ENDIAN, magicNumber, isBigEndian)) {
            return Endianess.LITTLE_ENDIAN;
        } else {
            return Endianess.NO_ENDIANESS;
        }
    }

    private int parseBlockLength(byte[] length, boolean isBigEndian) {
        if (isBigEndian) {
            int blockLength = (((data[0] << 32) & 0xFF) + ((data[1] << 16) & 0xFF) + ((data[2] << 8) & 0xFF) + (
                    (data[3] << 0) & 0xFF));
            return blockLength;
        } else {
            int blockLength = (((length[0] << 0) & 0xFF) + ((length[1] << 8) & 0xFF00) + ((length[2] << 16) &
                    0xFF0000) + ((length[3] << 32) & 0xFF000000));
            return blockLength;
        }
    }

    /**
     * Parse data block of all type of section and return current index to be read next
     *
     * @param data
     * @return
     */
    private int parseDataBlock(BlockTypes type, byte[] data, int initIndex) {
        try {

            int index = initIndex;

            int blockLength = parseBlockLength(Arrays.copyOfRange(data, index + 4, index + 8), isBigEndian);

            // substract 4 for header and 4 for size (x2 at the end)
            byte[] dataBlock = Arrays.copyOfRange(data, index + 8, index + (blockLength - 4));

            byte[] dataTemp = dataBlock;

            if (type == BlockTypes.SECTION_HEADER_BLOCK) {
                dataTemp = Arrays.copyOfRange(dataBlock, 4, dataBlock.length);
            }
            PcapNgStructureParser structure = new PcapNgStructureParser(type, dataTemp, isBigEndian);
            structure.decode();
            pcapSectionList.add(structure.getPcapStruct());

            index += (blockLength - 1) + 1;
            return index;
        } catch (Exception e) {
            e.printStackTrace();
            return DecoderStatus.FAILED_STATUS;
        }
    }

    /**
     * Decode a specific section type from HeaderBLocks class
     */
    public int processSectionType(BlockTypes type, int initIndex) throws DecodeException {

        int index = initIndex;

        if (UtilFunctions.compare32Bytes(HeaderBlocks.SECTION_TYPE_LIST.get(type.toString()), Arrays.copyOfRange
                (data, index, index + 4), isBigEndian)) {
            if (type == BlockTypes.SECTION_HEADER_BLOCK) {
                byte endianess = detectEndianness(Arrays.copyOfRange(Arrays.copyOfRange(data, index + 8,
                        index + 12), 0, 4));

                if (endianess == Endianess.BIG_ENDIAN) {
                    isBigEndian = true;
                } else if (endianess == Endianess.LITTLE_ENDIAN) {
                    isBigEndian = false;
                }
            }

            index = parseDataBlock(type, data, index);

            if (index == -1) {
                throw new DecodeException();
            }
            return index;
        }
        return index;
    }

    /**
     * Decode
     */
    public byte decode() {
        if (data == null || data.length < 4) {
            return DecoderStatus.FAILED_STATUS;
        }

        int initIndex = 0;

        try {
            int formerIndex = 0;

            while (initIndex != data.length) {
                initIndex = processSectionType(BlockTypes.SECTION_HEADER_BLOCK, initIndex);
                initIndex = processSectionType(BlockTypes.INTERFACE_DESCRIPTION_BLOCK, initIndex);
                initIndex = processSectionType(BlockTypes.ENHANCES_PACKET_BLOCK, initIndex);
                initIndex = processSectionType(BlockTypes.SIMPLE_PACKET_BLOCK, initIndex);
                initIndex = processSectionType(BlockTypes.NAME_RESOLUTION_BLOCK, initIndex);
                initIndex = processSectionType(BlockTypes.INTERFACE_STATISTICS_BLOCK, initIndex);
                initIndex = processSectionType(BlockTypes.PACKET_BLOCK, initIndex);

                if (formerIndex == initIndex && formerIndex != 0) {
                    throw new DecodeException("File parsing error | format not recognized");
                }
                formerIndex = initIndex;
            }
        } catch (DecodeException e) {
            e.printStackTrace();
            return DecoderStatus.FAILED_STATUS;
        }
        return DecoderStatus.SUCCESS_STATUS;
    }

    public ArrayList<IPcapngType> getSectionList() {
        return pcapSectionList;
    }
}
