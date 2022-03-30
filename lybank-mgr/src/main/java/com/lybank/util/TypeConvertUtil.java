package com.lybank.util;

import java.io.ByteArrayOutputStream;

/**
 * 字节、整数、长整数等数据类型之间的拆分和转换。<br/>
 * 还包括BCD码，十六进制码，网络字节序等等相互转换
 *
 * @author chenligang@szslcpay.com
 * @version 1.0
 */
public class TypeConvertUtil {

    private final static String HEX = "0123456789ABCDEF";

    /**
     * 字节数组转换成整型。(网络字节序，高字节在前)
     *
     * @param b      字节数组。
     * @param offset 待转换字节开始的位置。
     * @return 整数形式。
     */
    public final static int byte2int(byte[] b, int offset) {
        return (b[offset + 3] & 0xff) | ((b[offset + 2] & 0xff) << 8) | ((b[offset + 1] & 0xff) << 16)
                | ((b[offset] & 0xff) << 24);
    }

    /**
     * 字节数组转换成整型。(网络字节序，高字节在前)
     *
     * @param b 字节数组。
     * @return 整数形式。
     */
    public final static int byte2int(byte[] b) {
        return (b[3] & 0xff) | ((b[2] & 0xff) << 8) | ((b[1] & 0xff) << 16) | ((b[0] & 0xff) << 24);
    }

    /**
     * 低字节在前，高字节在后字节流转换为整型
     *
     * @param buf    byte[]
     * @param offset int
     * @return int
     */
    public final static int Sbyte2int(byte[] buf, int offset) { // 读取4个.
        return (buf[offset] & 0xff) | ((buf[offset + 1] << 8) & 0xff00) | (((buf[offset + 2] << 16) & 0xff0000))
                | (((buf[offset + 3] << 24) & 0xff000000));
    }

    /**
     * 低字节在前，高字节在后整型转换为字符串
     *
     * @param n      int
     * @param buf    byte[]
     * @param offset int
     */
    public final static void Sint2byte(int n, byte[] buf, int offset) {
        buf[offset + 3] = (byte) (n >> 24);
        buf[offset + 2] = (byte) (n >> 16);
        buf[offset + 1] = (byte) (n >> 8);
        buf[offset + 0] = (byte) n;
    }

    /**
     * 整型转换成16进制,,负数最后要在最高位加1就是8
     *
     * @param nInt int
     * @return 16进制度字符串。
     */
    public final static String int2hex(int nInt) {
        int n = nInt, tmp;
        // 不断的移动，4个分一小段，，移位和与或操作。
        char[] bb = new char[8];
        for (int i = 0; i < 32; i += 4) {
            tmp = (int) ((n >> i) & 0x0000000f);
            tmp = getHex(tmp);
            bb[7 - i / 4] = (char) tmp;
        }
        return new String(bb);
    }

    public final static int hex2int(String hex) {
        int ret = 0;
        for (int i = 0, len = hex.length(); i < len; i++) {
            ret = (ret << 8) + HEX.indexOf(hex.charAt(i));
        }

        return ret;
    }

    public final static long hex2long(String hex) {
        String hexStr = hex.toUpperCase();
        long ret = 0;
        for (int i = 0, len = hexStr.length(); i < len; i++) {
            ret = (ret << 8) + HEX.indexOf(hexStr.charAt(i));
        }

        return ret;
    }

    // 默认要求是大写十六进制，否则出错
    public final static byte hex2Byte(char hex) {
        byte b = (byte) HEX.indexOf(hex);
        return b;
    }

    /**
     * 将字节内容按照二进制码打印出来
     *
     * @param buf byte
     * @return String
     */
    public final static String byte2binary(byte buf) {
        StringBuilder chars = new StringBuilder();
        int inch = 0;
        int j;
        for (j = 7; j >= 0; j--) {
            inch = ((buf >> j) & 0x01);
            if (inch == 0) {
                chars.append('0');
            } else {
                chars.append('1');
            }
        }
        return chars.toString();
    }

    /**
     * 将字节数组内容按照二进制码打印出来
     *
     * @param buf byte[]
     * @return String
     */
    public final static String bytes2binary(byte[] buf) {
        int len = buf.length;
        StringBuilder chars = new StringBuilder();
        byte tmp;
        int inch = 0;
        int j;
        for (int i = 0; i < len; i++) {
            tmp = buf[i];
            for (j = 7; j >= 0; j--) {
                inch = ((tmp >> j) & 0x01);
                if (inch == 0) {
                    chars.append('0');
                } else {
                    chars.append('1');
                }
            }
        }
        return chars.toString();
    }

    /**
     * 将二进制码转换为字节数组
     *
     * @param buf byte[]
     * @return String
     */
    public final static byte[] binary2bytes(String data) {
        int length = data.length();
        if ((length % 8) != 0) {
            data = "0000000" + data;
        }

        data = data.substring(data.length() % 8, data.length());

        byte[] tmp = new byte[length / 8];
        for (int i = 0, index = 0; i < length; i = i + 8, index++) {
            tmp[index] = (byte) Integer.parseInt(data.substring(i, i + 8), 2);
        }

        return tmp;
    }

    /**
     * 将由0,1组成的长度为4的字符串转化为对应的16进制字符串表示，例如输入字符串"1111",输出值为"F" <br/>
     * 注意此方法输入的字符串长度最大为4，一次只能转换成为一个16进制字符
     *
     * @param s 0,1组成的字符串
     * @return 转换后的16进制字符
     */
    public static char binary2Hex(String s) {
        int len = s.length();
        if (len > 4)
            return '\0';

        if ((len % 4) != 0) {
            s = "000" + s;
            s = s.substring(s.length() % 4, s.length());
        }

        int result = Integer.parseInt(s, 2);
        switch (result) {
            case 0:
                return '0';
            case 1:
                return '1';
            case 2:
                return '2';
            case 3:
                return '3';
            case 4:
                return '4';
            case 5:
                return '5';
            case 6:
                return '6';
            case 7:
                return '7';
            case 8:
                return '8';
            case 9:
                return '9';
            case 10:
                return 'A';
            case 11:
                return 'B';
            case 12:
                return 'C';
            case 13:
                return 'D';
            case 14:
                return 'E';
            case 15:
                return 'F';
            default:
                return '\0';
        }
    }

    /**
     * 将0,1组成的字符串转换为对应的十六进制字符串，例如输入字符串"111100001111"输出为"FOF" 此方法输入的字符串长度不限
     *
     * @param s 0,1组成的字符串
     * @return 十六进制字符串
     */
    public static String binary2hex(String data) {
        int length = data.length();
        if ((length % 4) != 0) {
            data = "000" + data;
            data = data.substring(data.length() % 4, data.length());
        }

        StringBuilder str = new StringBuilder();

        for (int i = 0, len = data.length(); i < len / 4; i++) {
            str.append(binary2Hex(data.substring(i * 4, i * 4 + 4)));
        }
        return str.toString();
    }

    /**
     * BCD码转为10进制串(阿拉伯数据)<br/>
     * * BCD与十进制字符串的转换.<br/>
     * BCD（Binary Coded Decimal）是用二进制编码表示的十进制数，<br/>
     * 十进制数采用0~9十个数字，是人们最常用的。在计算机中，同一个数可以用两种BCD格式来表示：<br/>
     * ①压缩的BCD码 ②非压缩的BCD码<br/>
     * 压缩的BCD码：<br/>
     * 压缩的BCD码用4位二进制数表示一个十进制数位，整个十进制数用一串BCD码来表示。<br/>
     * 例如，十进制数59表示成压缩的BCD码为0101 1001，十进制数1946表示成压缩的BCD码为0001 1001 0100 0110。<br/>
     * 非压缩的BCD码：<br/>
     * 非压缩的BCD码用8位二进制数表示一个十进制数位，其中低4位是BCD码，高4位是0。<br/>
     * 例如，十进制数78表示成压缩的BCD码为0111 1000。<br/>
     * 从键盘输入数据时，计算机接收的是ASCII码，要将ASCII码表示的数转换成BCD码是很简单的，<br/>
     * 只要把ASCII码的高4位清零即可。反之，如果要把BCD码转换成ASII码，只要把BCD码 "或|"00110000即可。
     *
     * @param bytes BCD码
     * @return String 10进制串
     */
    public static String bcd2Str(byte[] bytes) {
        if (bytes.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append((char) getHex(((bytes[i] & 0xff) >> 4)));
            sb.append((char) getHex(((bytes[i] & 0x0f))));
        }
        return sb.toString();
    }

    /**
     * 10进制串转为BCD码<br/>
     *
     * @param asc 10进制串
     * @return byte[] BCD码
     */
    public static byte[] str2Bcd(String data) {
        if (data.length() == 0) {
            return new byte[0];
        }

        String str = data;
        // 奇数个数字需左补零
        if (str.length() % 2 != 0) {
            str = "0" + str;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i += 2) {
            int high = 0;
            if (cs[i] >= '0' && cs[i] <= '9') {
                high = cs[i] - 48;
            }
            if (cs[i] >= 'A' && cs[i] <= 'Z') {
                high = cs[i] - 55;
            }
            if (cs[i] >= 'a' && cs[i] <= 'z') {
                high = cs[i] - 'a' + 10;
            }

            int low = 0;
            if (cs[i + 1] >= '0' && cs[i + 1] <= '9') {
                low = cs[i + 1] - 48;
            }
            if (cs[i + 1] >= 'A' && cs[i + 1] <= 'Z') {
                low = cs[i + 1] - 55;
            }
            if (cs[i + 1] >= 'a' && cs[i + 1] <= 'z') {
                low = cs[i + 1] - 'a' + 10;
            }
            baos.write(high << 4 | low);
        }
        return baos.toByteArray();
    }

    /**
     * 将字节数组内容按照十六进制码打印出来
     *
     * @param buf   byte[]
     * @param split String 分隔符
     * @return String
     */
    public final static String bytes2hex(byte[] buf, String split) {
        if ((split == null) || (split.length() == 0)) {
            return bytes2hex(buf);
        }

        if ((buf == null) || (buf.length == 0)) {
            return "";
        }
        StringBuilder chars = new StringBuilder();
        for (byte tmp : buf) {
            chars.append(split);
            int inch = ((tmp >> 4) & 0x0F);
            inch = getHex(inch);
            chars.append((char) inch);

            inch = (tmp & 0x0F);
            inch = getHex(inch);
            chars.append((char) inch);
        }

        return chars.substring(split.length());
    }

    /**
     * 将字节数组内容按照十六进制码打印出来
     *
     * @param buf   byte[]
     * @param split String 分隔符
     * @return String
     */
    public final static String bytes2hex(byte[] buf) {
        if ((buf == null) || (buf.length == 0)) {
            return "";
        }

        StringBuilder chars = new StringBuilder();
        for (byte b : buf) {
            int inch = ((b >> 4) & 0x0F);
            inch = getHex(inch);
            chars.append((char) inch);

            inch = (b & 0x0F);
            inch = getHex(inch);
            chars.append((char) inch);
        }

        return chars.toString();
    }

    private static int getHex(int inch) {
        if (inch >= 10) {
            return inch + 55;
        } else {
            return inch + 48;
        }
    }

    public static String byte2hex(byte b) {
        String hex = "";
        int inch = ((b >> 4) & 0x0F);
        inch = getHex(inch);
        hex += (char) inch;

        inch = (b & 0x0F);
        inch = getHex(inch);
        hex += (char) inch;
        return hex;
    }

    /**
     * 将十六进制码字符串转换成字节数组内容
     *
     * @param str String
     * @return String
     */
    public final static byte[] hex2bytes(String str) {
        str = str.toUpperCase();
        int len = str.length() / 2;
        byte[] buf = new byte[len];
        int inch = 0;
        for (int i = 0; i < len; i++) {
            inch = str.charAt(i + i);
            if (inch >= 65) {
                buf[i] = (byte) (((inch - 55) << 4) & 0xF0);
            } else {
                buf[i] = (byte) (((inch - 48) << 4) & 0xF0);
            }
            inch = str.charAt(i + i + 1);
            if (inch >= 65) {
                buf[i] = (byte) (buf[i] | ((inch - 55) & 0x0F));
            } else {
                buf[i] = (byte) (buf[i] | ((inch - 48) & 0x0F));
            }

        }
        return buf;
    }

    /***
     * 根据实际长度返回,不补任何东西.
     *
     * @param aa int
     * @return String 一个16进制的字符串.
     */
    public final static String int2hexA(int aa) {
        char[] cc = new char[8];
        int len = 0;
        int tmp = 0;
        for (int offset = 28; offset >= 0; offset -= 4) {
            tmp = (aa >> offset) & 0x0f;
            if ((tmp == 0) && (len == 0)) { // 开头是0则省略调.
                continue;
            } else {
                tmp = getHex(tmp);
            }
            // 遇到正常字符.
            cc[len++] = (char) tmp;
        }
        return new String(cc, 0, len);
    }

    /***
     * 根据实际长度返回,不补任何东西.
     *
     * @param aa long
     * @return String 一个16进制的字符串.
     */
    public final static String long2hexA(long aa) {
        char[] cc = new char[16];
        int len = 0;
        int tmp = 0;
        for (int offset = 60; offset >= 0; offset -= 4) {
            tmp = ((int) (aa >> offset)) & 0x0f;
            if ((tmp == 0) && (len == 0)) { // 开头是0则省略调.
                continue;
            } else {
                tmp = getHex(tmp);
            }
            // 遇到正常字符.
            cc[len++] = (char) tmp;
        }
        return new String(cc, 0, len);
    }

    public final static void main(String[] args) {
        char a = 'A';
        System.out.printf("%d", (int) a);
        System.out.println();
        System.out.println(TypeConvertUtil.bytes2binary("asdf".getBytes()));
        System.out.println(TypeConvertUtil.bytes2hex("asdf".getBytes(), " "));
        byte[] buf = TypeConvertUtil.hex2bytes("f1234f");
        System.out.println(TypeConvertUtil.bytes2hex(buf, ""));
        System.out.println(TypeConvertUtil.int2hex(255).substring(6));
    }

    /**
     * 字节数组转换成长整型。(网络字节序，高字节在前)
     *
     * @param b 字节数组。
     * @return 长整数形式。
     */
    public final static long byte2long(byte[] b) {
        return ((long) b[7] & 0xff) | (((long) b[6] & 0xff) << 8) | (((long) b[5] & 0xff) << 16)
                | (((long) b[4] & 0xff) << 24) | (((long) b[3] & 0xff) << 32) | (((long) b[2] & 0xff) << 40)
                | (((long) b[1] & 0xff) << 48) | ((long) b[0] << 56);
    }

    /**
     * 字节数组转换成长整型。(网络字节序，高字节在前)
     *
     * @param b      byte[] 字节数组。
     * @param offset int
     * @return long 长整数形式。
     */
    public final static long byte2long(byte[] b, int offset) {
        return ((long) b[offset + 7] & 0xff) | (((long) b[offset + 6] & 0xff) << 8)
                | (((long) b[offset + 5] & 0xff) << 16) | (((long) b[offset + 4] & 0xff) << 24)
                | (((long) b[offset + 3] & 0xff) << 32) | (((long) b[offset + 2] & 0xff) << 40)
                | (((long) b[offset + 1] & 0xff) << 48) | ((long) b[offset] << 56);
    }

    /**
     * 整型转换成字节。(网络字节序，高字节在前)
     *
     * @param n 整数。
     * @return 长度为4的字节数组。
     */
    public final static byte[] int2byte(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n >> 24);
        b[1] = (byte) (n >> 16);
        b[2] = (byte) (n >> 8);
        b[3] = (byte) n;
        return b;
    }

    /**
     * 整型转换成字节。(网络字节序，高字节在前)
     *
     * @param n      整数。
     * @param buf    存放转换结果的字节数组。
     * @param offset 存放位置的偏移地址。
     */
    public final static void int2byte(int n, byte[] buf, int offset) {
        buf[offset] = (byte) (n >> 24);
        buf[offset + 1] = (byte) (n >> 16);
        buf[offset + 2] = (byte) (n >> 8);
        buf[offset + 3] = (byte) n;
    }

    /**
     * 短整型转换成字节。(网络字节序，高字节在前)
     *
     * @param n 整数。
     * @return 长度为4的字节数组。
     */
    public final static byte[] short2byte(int n) {
        byte[] b = new byte[2];
        b[0] = (byte) (n >> 8);
        b[1] = (byte) n;
        return b;
    }

    /**
     * 短整型转换成字节。(网络字节序，高字节在前)
     *
     * @param n      整数。
     * @param buf    存放转换结果的字节数组。
     * @param offset 存放位置的偏移地址。
     */
    public final static void short2byte(int n, byte[] buf, int offset) {
        buf[offset] = (byte) (n >> 8);
        buf[offset + 1] = (byte) n;
    }

    /**
     * 长整型转换成字节。(网络字节序，高字节在前)
     *
     * @param n 长整数。
     * @return 长度为8的字节数组。
     */
    public final static byte[] long2byte(long n) {
        byte[] b = new byte[8];
        // b[0]=(byte)(n>>57); // comment by edong 20011203
        b[0] = (byte) (n >> 56);
        b[1] = (byte) (n >> 48);
        b[2] = (byte) (n >> 40);
        b[3] = (byte) (n >> 32);
        b[4] = (byte) (n >> 24);
        b[5] = (byte) (n >> 16);
        b[6] = (byte) (n >> 8);
        b[7] = (byte) n;
        return b;
    }

    /**
     * 长整型转换成字节。(网络字节序，高字节在前)
     *
     * @param n      长整数。
     * @param buf    存放转换结果的字节数组。
     * @param offset 存放位置的偏移地址。
     */
    public final static void long2byte(long n, byte[] buf, int offset) {
        // buf[offset]=(byte)(n>>57); // comment by edong
        // 20011203
        buf[offset] = (byte) (n >> 56);
        buf[offset + 1] = (byte) (n >> 48);
        buf[offset + 2] = (byte) (n >> 40);
        buf[offset + 3] = (byte) (n >> 32);
        buf[offset + 4] = (byte) (n >> 24);
        buf[offset + 5] = (byte) (n >> 16);
        buf[offset + 6] = (byte) (n >> 8);
        buf[offset + 7] = (byte) n;
    }

    // **************低字节在前*******************

    /**
     * 长整型转换成字节。(网络字节序，高字节在前)
     *
     * @param n      长整数。
     * @param buf    存放转换结果的字节数组。
     * @param offset 存放位置的偏移地址。
     * @author zhangyong 2004-05-27
     */
    public final static void Slong2byte(long n, byte[] buf, int offset) {
        buf[offset + 7] = (byte) (n >> 56);
        buf[offset + 6] = (byte) (n >> 48);
        buf[offset + 5] = (byte) (n >> 40);
        buf[offset + 4] = (byte) (n >> 32);
        buf[offset + 3] = (byte) (n >> 24);
        buf[offset + 2] = (byte) (n >> 16);
        buf[offset + 1] = (byte) (n >> 8);
        buf[offset] = (byte) n;
    }

    /**
     * 整型转换成字节。(网络字节序，低字节在前)
     *
     * @param iSource int 整数。
     * @param buf     byte[] 存放转换结果的字节数组。
     * @param offset  int 存放位置的偏移地址。
     * @return byte[]
     */
    static public byte[] inttobyte(int iSource, byte[] buf, int offset) {
        buf[offset] = (byte) iSource;
        buf[offset + 1] = (byte) (iSource >> 8);
        buf[offset + 2] = (byte) (iSource >> 16);
        buf[offset + 3] = (byte) (iSource >> 24);
        return (buf);
    }

    /**
     * 短整数转换成字节。(网络字节序，低字节在前)
     *
     * @param n      短整数。
     * @param buf    存放转换结果的字节数组。
     * @param offset 存放位置的偏移地址。
     * @author zhangyong 2004-05-27
     */

    public final static void shorttobyte(int n, byte[] buf, int offset) {
        buf[offset] = (byte) n;
        buf[offset + 1] = (byte) (n >> 8);
    }

    /**
     * 短整数转换成字节。(网络字节序，低字节在前)
     *
     * @param b      byte[] 存放转换结果的字节数组。
     * @param offset int 存放位置的偏移地址。
     * @return int
     */
    public final static int bytetoshort(byte[] b, int offset) {
        return (b[offset] & 0xff) | ((b[offset + 1] & 0xff) << 8);
    }

    /**
     * 字节数组转换成整型。(网络字节序，低字节在前)
     *
     * @param b      字节数组。
     * @param offset 待转换字节开始的位置。
     * @return 整数形式。
     */
    public final static int bytetoint(byte[] b, int offset) {
        return (b[offset] & 0xff) | ((b[offset + 1] & 0xff) << 8) | ((b[offset + 2] & 0xff) << 16)
                | ((b[offset + 3] & 0xff) << 24);
    }

    /**
     * 字节数组转换成整型。(网络字节序，低字节在前)
     *
     * @param b 字节数组。
     * @return 整数形式。
     */
    public final static int bytetoint(byte[] b) {
        return (b[0] & 0xff) | ((b[1] & 0xff) << 8) | ((b[2] & 0xff) << 16) | ((b[3] & 0xff) << 24);
    }

    /**
     * 字节数组转换成长整型。(网络字节序，低字节在前)
     *
     * @param b 字节数组。
     * @return 长整数形式。
     */
    public final static long bytetolong(byte[] b) {
        // System.out.println("typeconvert="+((int)b[7]));
        // System.out.println("typeconvert"+((int)b[6]));
        // System.out.println("typeconvert"+((int)b[5]));
        // System.out.println("typeconvert"+((int)b[4]));
        // System.out.println("typeconvert"+((int)b[3]));
        // System.out.println("typeconvert"+((int)b[2]));
        // System.out.println("typeconvert"+((int)b[1]));
        // System.out.println("typeconvert"+((int)b[0]));
        return ((long) b[0] & 0xff) | (((long) b[1] & 0xff) << 8) | (((long) b[2] & 0xff) << 16)
                | (((long) b[3] & 0xff) << 24) | (((long) b[4] & 0xff) << 32) | (((long) b[5] & 0xff) << 40)
                | (((long) b[6] & 0xff) << 48) | ((long) b[7] << 56);
    }

    /**
     * 字节数组转换成长整型。(网络字节序，高字节在前)
     *
     * @param b      byte[] 字节数组。
     * @param offset int
     * @return long 长整数形式。
     */
    public final static long bytetolong(byte[] b, int offset) {
        return ((long) b[offset] & 0xff) | (((long) b[offset + 1] & 0xff) << 8) | (((long) b[offset + 2] & 0xff) << 16)
                | (((long) b[offset + 3] & 0xff) << 24) | (((long) b[offset + 4] & 0xff) << 32)
                | (((long) b[offset + 5] & 0xff) << 40) | (((long) b[offset + 6] & 0xff) << 48)
                | ((long) b[offset + 7] << 56);
    }
}
