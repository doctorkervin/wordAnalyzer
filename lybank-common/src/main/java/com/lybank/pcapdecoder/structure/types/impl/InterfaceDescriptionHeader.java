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

import com.lybank.model.constant.LinkLayerConstants;
import com.lybank.pcapdecoder.structure.BlockTypes;
import com.lybank.pcapdecoder.structure.options.OptionParser;
import com.lybank.pcapdecoder.structure.options.inter.IOptionsDescriptionHeader;
import com.lybank.pcapdecoder.structure.types.IPcapngType;
import com.lybank.pcapdecoder.structure.types.inter.IDescriptionBlock;
import com.lybank.pcapdecoder.utils.UtilFunctions;

import java.util.Arrays;


/**
 * Implementation for INTERFACE DESCRIPTION HEADER
 * <p/>
 * 0                   1                   2                   3
 * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 * +---------------------------------------------------------------+
 * 0 |                    Block Type = 0x00000001                    |
 * +---------------------------------------------------------------+
 * 4 |                      Block Total Length                       |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * 8 |           LinkType            |           Reserved            |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * 12 |                            SnapLen                            |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * 16 /                                                               /
 * /                      Options (variable)                       /
 * /                                                               /
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                      Block Total Length                       |
 * +---------------------------------------------------------------+
 *
 * @author Bertrand Martel
 */
public class InterfaceDescriptionHeader implements IDescriptionBlock, IPcapngType {

    private int snapLen = -1;

    private String linkTypeStr = "";

    private int linkType = -1;

    private IOptionsDescriptionHeader options = null;

    public InterfaceDescriptionHeader(byte[] data, boolean isBigEndian, BlockTypes type) {

        //may be used later for further specifications
        int reserved = -1;

        if (isBigEndian) {
            linkType = UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 0, 2));
            linkTypeStr = LinkLayerConstants.LINK_LAYER_LIST.get(linkType);
            reserved = UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 2, 4));
            snapLen = UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 4, 8));
        } else {
            linkType = UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 2)));
            linkTypeStr = LinkLayerConstants.LINK_LAYER_LIST.get(linkType);
            reserved = UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 2, 4)));
            snapLen = UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 4, 8)));
        }

        if (data.length > 8) {
            //parse set of options
            OptionParser optionParser = new OptionParser(Arrays.copyOfRange(data, 8, data.length), isBigEndian, type,
                    false);
            optionParser.decode();
            this.options = (IOptionsDescriptionHeader) optionParser.getOption();
        }
    }

    @Override
    public int getSnapLen() {
        return snapLen;
    }

    @Override
    public String getLinkType() {
        return linkTypeStr;
    }

    @Override
    public IOptionsDescriptionHeader getOptions() {
        return options;
    }

}
