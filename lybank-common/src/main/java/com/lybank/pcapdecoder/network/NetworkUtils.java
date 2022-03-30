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
package com.lybank.pcapdecoder.network;

import com.lybank.pcapdecoder.utils.UtilFunctions;

/**
 * Useful functions for network
 *
 * @author Bertrand Martel
 */
public class NetworkUtils {

    public static String formatIpv4Addr(byte[] ip) {
        String ipStr = "";

        for (int i = 0; i < ip.length; i++) {
            ipStr += (ip[i] & 0xFF) + ".";
        }
        return ipStr.substring(0, ipStr.length() - 1);
    }

    public static String formatIpv6Addr(byte[] ip) {
        String ipStr = "";

        for (int i = 0; i < 16; i = i + 2) {
            ipStr += UtilFunctions.convertFromIntToHexa(ip[i]) + UtilFunctions.convertFromIntToHexa(ip[i + 1]) + ":";
        }

        return ipStr.substring(0, ipStr.length() - 1);
    }

    public static String formatMacAddr(byte[] macAddr) {
        String macAddrStr = "";
        for (int i = 0; i < macAddr.length; i++) {
            macAddrStr += UtilFunctions.convertFromIntToHexa(macAddr[i]) + ":";
        }

        return macAddrStr.substring(0, macAddrStr.length() - 1);
    }


    public static String formatIpv6AddrWithPort(byte[] ip) {
        String ipStr = "";

        for (int i = 0; i < 16; i = i + 2) {
            ipStr += UtilFunctions.convertFromIntToHexa(ip[i]) + UtilFunctions.convertFromIntToHexa(ip[i + 1]) + ":";
        }
        ipStr = ipStr.substring(0, ipStr.length() - 1) + "/" + (ip[16] & 0xFF);

        return ipStr;
    }

}
