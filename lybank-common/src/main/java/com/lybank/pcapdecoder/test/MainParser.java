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

import com.lybank.pcapdecoder.PcapDecoder;
import com.lybank.pcapdecoder.utils.DecoderStatus;

import java.io.IOException;

/**
 * @mainpage PCAP NG JAVA File parser
 * <p/>
 * COMMAND LINE SYNTAX :
 * <p/>
 * java -jar pcapngdecoder-1.0.jar -f test.pcapng -v
 * <p/>
 * <ul>
 * <li>-f <file.pcapng> : input file</li>
 * <li>-v               : verbose, will show all section parsing content</li>
 * </ul>
 * <p/>
 * <p>
 * For now following sections are available to be parsed :
 * <ul>
 * <li>Section Header</li>
 * <li>Interface Description</li>
 * <li>Interface Statistics</li>
 * <li>Enhanced Packet</li>
 * <li>Name Resolution</li>
 * </ul>
 * </p>
 * <p/>
 * Specification from <a>https://www.winpcap.org/ntar/draft/PCAP-DumpFileFormat.html</a>
 */

/**
 * Start PCAP NG file decoder
 *
 * @author Bertrand Martel <bertrandmartel92@gmail.com>
 */
public class MainParser {

    /**
     * logger.
     */

    /**
     * Start PCAP NG decoder
     *
     * @param args
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        String inputFile = "/Users/mac/Projects/pacpAnalyzer/file/pcapfile/xxxx.pcapng";

        PcapDecoder pcapNgDecoder = new PcapDecoder(inputFile);
        int status = pcapNgDecoder.decode();

        if (status == DecoderStatus.SUCCESS_STATUS) {

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;

            System.out.println("Decoding time : " + totalTime + " millis");

            if (true) {
                DisplayAllPacket.displayResult(pcapNgDecoder, null);
            }
        } else
            System.err.println("Decoder failure");

    }
}
