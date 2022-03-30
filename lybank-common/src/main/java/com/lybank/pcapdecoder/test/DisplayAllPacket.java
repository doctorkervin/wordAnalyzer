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
package com.lybank.pcapdecoder.test;


import com.lybank.model.constant.PacketBoundState;
import com.lybank.model.constant.PacketHashType;
import com.lybank.model.constant.PacketReceptionType;
import com.lybank.pcapdecoder.PcapDecoder;
import com.lybank.pcapdecoder.structure.options.inter.IOptionSectionHeader;
import com.lybank.pcapdecoder.structure.options.inter.IOptionsDescriptionHeader;
import com.lybank.pcapdecoder.structure.options.inter.IOptionsEnhancedPacketHeader;
import com.lybank.pcapdecoder.structure.options.inter.IOptionsStatisticsHeader;
import com.lybank.pcapdecoder.structure.types.inter.*;
import com.lybank.pcapdecoder.utils.UtilFunctions;

import java.util.Date;
import java.util.logging.Logger;

public class DisplayAllPacket {

    /**
     * Display all information on packets (highly consuming when used on big file)
     *
     * @param decoder
     */
    public static void displayResult(PcapDecoder decoder, Logger logger) {

        logger.info("##########################################################");

        int timestampResolution = 3;

        for (int i = 0; i < decoder.getSectionList().size(); i++) {

            if (decoder.getSectionList().get(i) instanceof ISectionHeaderBlock) {

                ISectionHeaderBlock temp = (ISectionHeaderBlock) decoder.getSectionList().get(i);

                logger.info("SECTION HEADER BLOCK");
                if (temp.getMajorVersion() != -1)
                    logger.info("Major version      : " + temp.getMajorVersion());

                if (temp.getMinorVersion() != -1)
                    logger.info("Minor version      : " + temp.getMinorVersion());

                IOptionSectionHeader optionsList = temp.getOptions();

                if (optionsList != null) {
                    if (!optionsList.getHardware().equals(""))
                        logger.info("hardware interface : " + optionsList.getHardware());
                    if (!optionsList.getOS().equals(""))
                        logger.info("OS                 : " + optionsList.getOS());
                    if (!optionsList.getUserAppl().equals(""))
                        logger.info("user application   : " + optionsList.getUserAppl());
                    if (!optionsList.getComment().equals(""))
                        logger.info("comment            : " + optionsList.getComment());
                }
                logger.info("##########################################################");
            } else if (decoder.getSectionList().get(i) instanceof IDescriptionBlock) {
                
                IDescriptionBlock temp = (IDescriptionBlock) decoder.getSectionList().get(i);

                logger.info("SECTION INTERFACE DESCRIPTION BLOCK");

                if (!temp.getLinkType().equals(""))
                    logger.info("Link type             : " + temp.getLinkType());
                if (temp.getSnapLen() != -1)
                    logger.info("Snap len              : " + temp.getSnapLen());

                IOptionsDescriptionHeader optionsList = temp.getOptions();

                if (optionsList != null) {
                    if (!optionsList.getInterfaceName().equals(""))
                        logger.info("interface name        : " + optionsList.getInterfaceName());
                    if (!optionsList.getInterfaceDescription().equals(""))
                        logger.info("interface description : " + optionsList.getInterfaceDescription());
                    if (!optionsList.getInterfaceIpv4NetworkAddr().equals(""))
                        logger.info("interface ipv4 addr   : " + optionsList.getInterfaceIpv4NetworkAddr());
                    if (!optionsList.getInterfaceNetmask().equals(""))
                        logger.info("interface netmask     : " + optionsList.getInterfaceNetmask());
                    if (!optionsList.getIpv6NetworkAddr().equals(""))
                        logger.info("interface ipv6 addr   : " + optionsList.getIpv6NetworkAddr());
                    if (!optionsList.getInterfaceMacAddr().equals(""))
                        logger.info("interface mac addr    : " + optionsList.getInterfaceMacAddr());
                    if (!optionsList.getInterfaceEuiAddr().equals(""))
                        logger.info("interface EUI addr    : " + optionsList.getInterfaceEuiAddr());
                    if (optionsList.getInterfaceSpeed() != -1)
                        logger.info("interface speed       : " + optionsList.getInterfaceSpeed() + "bps");
                    if (optionsList.getTimeStampResolution() != -1) {
                        logger.info("timestamp resolution  : " + optionsList.getTimeStampResolution());
                        timestampResolution = optionsList.getTimeStampResolution();
                    }
                    if (optionsList.getTimeBias() != -1)
                        logger.info("time offset from UTC  : " + optionsList.getTimeBias());
                    if (!optionsList.getInterfaceFilter().equals(""))
                        logger.info("interface filter      : " + optionsList.getInterfaceFilter());
                    if (!optionsList.getInterfaceOperatingSystem().equals(""))
                        logger.info("interface OS name     : " + optionsList.getInterfaceOperatingSystem());
                    if (optionsList.getInterfaceFrameCheckSequenceLength() != -1)
                        logger.info("interface FCS length  : " + optionsList
                                .getInterfaceFrameCheckSequenceLength());
                    if (optionsList.getTimeStampOffset() != -1)
                        logger.info("timestamp offset      : " + optionsList.getTimeStampOffset());
                    if (!optionsList.getComment().equals(""))
                        logger.info("comment               : " + optionsList.getComment());
                }
                logger.info("##########################################################");
            } else if (decoder.getSectionList().get(i) instanceof IEnhancedPacketBLock) {
                IEnhancedPacketBLock temp = (IEnhancedPacketBLock) decoder.getSectionList().get(i);

                logger.info("SECTION ENHANCED PACKET BLOCK");
                if (temp.getInterfaceId() != -1)
                    logger.info("interface id             : " + temp.getInterfaceId());

                if (temp.getTimeStamp() != -1) {
                    if (timestampResolution == 3) {
                        logger.info("timestamp in millis      : " + new Date(temp.getTimeStamp()));
                    } else if (timestampResolution == 6) {
                        logger.info("timestamp in millis      : " + new Date(temp.getTimeStamp() / 1000));
                    }
                }
                if (temp.getCapturedLength() != -1)
                    logger.info("captured length          : " + temp.getCapturedLength());
                if (temp.getPacketLength() != -1)
                    logger.info("packet length            : " + temp.getPacketLength());
                if (temp.getPacketData() != null)
                    logger.info(UtilFunctions.byteArrayToStringMessage("packet data             ", temp
                            .getPacketData(), '|'));

                IOptionsEnhancedPacketHeader optionsList = temp.getOptions();

                if (optionsList != null) {
                    if (optionsList.getPacketBound() != PacketBoundState.UNKNOWN)
                        logger.info("packet bound state     : " + optionsList.getPacketBound());
                    if (optionsList.getPacketReceptionType() != PacketReceptionType.UNKNOWN)
                        logger.info("packet reception type  : " + optionsList.getPacketReceptionType());
                    if (optionsList.getFrameCheckSumLength() != -1)
                        logger.info("packet FCS length      : " + optionsList.getFrameCheckSumLength());
                    if (optionsList.getDropPacketCount() != -1)
                        logger.info("packet drop count      : " + optionsList.getDropPacketCount());
                    if (optionsList.getPacketHashType() != PacketHashType.UNKNOWN)
                        logger.info("packet hash type       : " + optionsList.getPacketHashType());
                    if (optionsList.getPacketHashBigEndian() != null)
                        logger.info(UtilFunctions.byteArrayToStringMessage("packet hash type", optionsList
                                .getPacketHashBigEndian(), '|'));

                    for (int j = 0; j < optionsList.getLinkLayerErrorList().size(); j++) {
                        logger.info(optionsList.getLinkLayerErrorList().get(i) + " detected");
                    }
                }
                logger.info("##########################################################");
            } else if (decoder.getSectionList().get(i) instanceof IStatisticsBlock) {
                IStatisticsBlock temp = (IStatisticsBlock) decoder.getSectionList().get(i);

                logger.info("SECTION INTERFACE STATISTICS BLOCK");

                if (temp.getInterfaceId() != -1)
                    logger.info("interface id             : " + temp.getInterfaceId());

                if (temp.getTimeStamp() != -1) {
                    if (timestampResolution == 3) {
                        logger.info("timestamp in millis      : " + new Date(temp.getTimeStamp()));
                    } else if (timestampResolution == 6) {
                        logger.info("timestamp in millis      : " + new Date(temp.getTimeStamp() / 1000));
                    }
                }

                IOptionsStatisticsHeader optionsList = temp.getOptions();

                if (optionsList != null) {
                    if (optionsList.getCaptureStartTime() != -1) {
                        if (timestampResolution == 3) {
                            logger.info("capture start time       : " + new Date(optionsList
                                    .getCaptureStartTime()));
                        } else if (timestampResolution == 6) {
                            logger.info("capture start time       : " + new Date(optionsList
                                    .getCaptureStartTime() / 1000));
                        }
                    }
                    if (optionsList.getCaptureEndTime() != -1) {
                        if (timestampResolution == 3) {
                            logger.info("capture end time         : " + new Date(optionsList.getCaptureEndTime
                                    ()));
                        } else if (timestampResolution == 6) {
                            logger.info("capture end time         : " + new Date(optionsList.getCaptureEndTime
                                    () / 1000));
                        }
                    }
                    if (optionsList.getPacketReceivedCount() != -1)
                        logger.info("packet received count    : " + optionsList.getPacketReceivedCount());
                    if (optionsList.getPacketDropCount() != -1)
                        logger.info("packet drop count        : " + optionsList.getPacketDropCount());
                    if (optionsList.getPacketAcceptedByFilterCount() != -1)
                        logger.info("packet accepted by filter   : " + optionsList
                                .getPacketAcceptedByFilterCount());
                    if (optionsList.getPacketDroppedByOS() != -1)
                        logger.info("packet dropped by OS        : " + optionsList.getPacketDroppedByOS());
                    if (optionsList.getPacketDeliveredToUser() != -1)
                        logger.info("packet delivered to user    : " + optionsList.getPacketDeliveredToUser());
                }

                logger.info("##########################################################");
            } else if (decoder.getSectionList().get(i) instanceof INameResolutionBlock) {
                INameResolutionBlock temp = (INameResolutionBlock) decoder.getSectionList().get(i);

                logger.info("SECTION NAME RESOLUTION BLOCK");

                if (temp.getRecords() != null) {
                    logger.info("IPV4 ENTRIES -------------------");
                    for (int j = 0; j < temp.getRecords().getIpv4DnsEntries().size(); j++) {
                        logger.info(temp.getRecords().getIpv4DnsEntries().get(j).getIpAddr());

                        for (int k = 0; k < temp.getRecords().getIpv4DnsEntries().get(j).getDnsEntries().size(); k++) {
                            System.out.print("\t" + temp.getRecords().getIpv4DnsEntries().get(j).getDnsEntries().get
                                    (k) + ";");
                        }
                        logger.info("");
                    }

                    logger.info("IPV6 ENTRIES -------------------");
                    for (int j = 0; j < temp.getRecords().getIpv6DnsEntries().size(); j++) {
                        logger.info(temp.getRecords().getIpv6DnsEntries().get(j).getIpAddr());

                        for (int k = 0; k < temp.getRecords().getIpv6DnsEntries().get(j).getDnsEntries().size(); k++) {
                            System.out.print("\t" + temp.getRecords().getIpv6DnsEntries().get(j).getDnsEntries().get
                                    (k) + ";");
                        }
                        logger.info("");
                    }
                }

                if (temp.getOptions() != null) {
                    if (!temp.getOptions().getDnsIpv4Addr().equals(""))
                        logger.info("DNS IPV4 address    : " + temp.getOptions().getDnsIpv4Addr());
                    if (!temp.getOptions().getDnsIpv6Addr().equals(""))
                        logger.info("DNS IPV6 address    : " + temp.getOptions().getDnsIpv6Addr());
                    if (!temp.getOptions().getDnsName().equals(""))
                        logger.info("DNS SERVER NAME     : " + temp.getOptions().getDnsName());
                }
                logger.info ("##########################################################");
            }
        }
    }
}
