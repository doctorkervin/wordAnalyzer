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
package com.lybank.pcapdecoder.structure.types.impl;

import com.lybank.pcapdecoder.structure.BlockTypes;
import com.lybank.pcapdecoder.structure.options.OptionParser;
import com.lybank.pcapdecoder.structure.options.inter.IOptionSectionHeader;
import com.lybank.pcapdecoder.structure.types.IPcapngType;
import com.lybank.pcapdecoder.structure.types.inter.ISectionHeaderBlock;
import com.lybank.pcapdecoder.utils.UtilFunctions;

import java.util.Arrays;

/**
 * Implementation for Section Header Block parsing in Pcap ng decoder
 * <p/>
 * * SECTION HEADER BLOCK
 * <p/>
 * 0                   1                   2                   3
 * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 * +---------------------------------------------------------------+
 * 0 |                   Block Type = 0x0A0D0D0A                     |
 * +---------------------------------------------------------------+
 * 4 |                      Block Total Length                       |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * 8 |                      Byte-Order Magic                         |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * 12 |          Major Version        |         Minor Version         |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * 16 |                                                               |
 * |                          Section Length                       |
 * |                                                               |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * 24 /                                                               /
 * /                      Options (variable)                       /
 * /                                                               /
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                      Block Total Length                       |
 * +---------------------------------------------------------------+
 *
 * @author Bertrand Martel
 */
public class SectionHeader implements ISectionHeaderBlock, IPcapngType {

    private int majorVersion = -1;

    private int minorVersion = -1;

    private int sectionLength = -1;

    private IOptionSectionHeader options = null;

    public SectionHeader(byte[] data, boolean isBigEndian, BlockTypes type) {

        if (isBigEndian) {
            minorVersion = UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 0, 2));
            majorVersion = UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 2, 4));
            sectionLength = UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 4, 12));
        } else {
            minorVersion = UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data,
                    0, 2)));
            majorVersion = UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data,
					2, 4)));
            sectionLength = UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data,
					4, 12)));
        }

        if (data.length > 11) {
            //parse set of options
            OptionParser optionParser = new OptionParser(Arrays.copyOfRange(data, 12, data.length), isBigEndian,
					type, false);
            optionParser.decode();
            this.options = (IOptionSectionHeader) optionParser.getOption();
        }
    }

    @Override
    public int getMajorVersion() {
        return majorVersion;
    }

    @Override
    public int getMinorVersion() {
        return minorVersion;
    }

    @Override
    public int getSectionLength() {
        return sectionLength;
    }

    @Override
    public IOptionSectionHeader getOptions() {
        return options;
    }

}
