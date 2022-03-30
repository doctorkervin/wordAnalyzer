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
package com.lybank.pcapdecoder.structure.options.inter;

import com.lybank.model.constant.LinkLayerError;
import com.lybank.model.constant.PacketBoundState;
import com.lybank.model.constant.PacketHashType;
import com.lybank.model.constant.PacketReceptionType;

import java.util.ArrayList;

/**
 * Interface template for Enhanced Packet Section
 *
 * @author Bertrand Martel
 */
public interface IOptionsEnhancedPacketHeader extends IOptions {

    /**
     * Retrieve bound state (inbound / outbound)
     *
     * @return
     */
    PacketBoundState getPacketBound();

    /**
     * Retriev reception state (broadcast / unicast / multicast / promiscuous)
     *
     * @return
     */
    PacketReceptionType getPacketReceptionType();

    /**
     * get FCS length
     *
     * @return
     */
    int getFrameCheckSumLength();

    /**
     * get drop packet count
     *
     * @return
     */
    int getDropPacketCount();

    /**
     * get packet hash type (2SCOMP / MD5 / CRC32/ SHA1  ...)
     *
     * @return
     */
    PacketHashType getPacketHashType();

    /**
     * get packet hash
     *
     * @return
     */
    byte[] getPacketHashBigEndian();

    /**
     * get a list of error (link layer dependant)
     *
     * @return
     */
    ArrayList<LinkLayerError> getLinkLayerErrorList();

}
