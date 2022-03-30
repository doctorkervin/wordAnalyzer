package com.lybank.util;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * ClassName: ThreeDesUtil <br/>
 * Function: TODO 类的作用：3des 加解密运算  16或 24字节密钥加解密工具类，没填充. <br/>
 * date: 2017年3月1日 上午10:36:17 <br/>
 *
 * @author lishaofeng  396191970@qq.com
 * @since JDK 1.8
 */

public class ThreeDesUtil {

    /**
     * @param args在java中调用sun公司提供的3DES加密解密算法时，需要使
     * 用到$JAVA_HOME/jre/lib/目录下如下的4个jar包：
     * jce.jar
     * security/US_export_policy.jar
     * security/local_policy.jar
     * ext/sunjce_provider.jar
     */

    public static final String CIPHER_ALGORITHM = "DESede/ECB/NoPadding";


    private static final String Algorithm = "DESede"; //定义加密算法,可用 DES,DESede,Blowfish

    public static void main(String[] args) {

        String data = "6224";
        String key = "21341241243423234213423412343321";

        String str = encryptPadFF(key, data);
        System.out.println("+密后的字符串:" + str);

        str = decryptSubPadFF(key, str);
        System.out.println("解密后的字符串:" + str);


    }

    //keybyte为加密密钥，长度为24字节
    //src为被加密的数据缓冲区（源）
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //加密
            Cipher c1 = Cipher.getInstance(CIPHER_ALGORITHM);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);//在单一方面的加密或解密
        } catch (java.security.NoSuchAlgorithmException e1) {
            // TODO: handle exception
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    //keybyte为加密密钥，长度为24字节
    //src为加密后的缓冲区
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //解密
            Cipher c1 = Cipher.getInstance(CIPHER_ALGORITHM);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            // TODO: handle exception
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    //转换成十六进制字符串
    public static String byte2Hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
            if (n < b.length - 1) hs = hs + ":";
        }
        return hs.toUpperCase();
    }

    public static byte[] hex(String username) {
        String key = "test";//关键字
        String f = DigestUtils.md5Hex(username + key);
        byte[] bkeys = new String(f).getBytes();
        byte[] enk = new byte[24];
        for (int i = 0; i < 24; i++) {
            enk[i] = bkeys[i];
        }
        return enk;
    }


    public static String decryptSubPadFF(String key, String data) {
        String str = decryptMode(key, data);
        if (str.indexOf('F') > 0)
            str = str.substring(0, str.indexOf('F'));
        return str;
    }

    public static String decryptIDCard(String key, String data) {
        String str = decryptMode(key, data);
        if (str.indexOf('F') > 0)
            str = str.substring(0, str.indexOf('F'));

        str = str.toUpperCase();
        str = str.replace('D', 'X');

        return str;
    }

    /**
     * decryptMode:(方法的作用：). <br/>
     * TODO(注意事项 ：
     * hex字符串 举例“12323423” 函数内会进行转换为[0x12,0x32,0x34,0x23] 长度指转化后的长度).<br/>
     *
     * @param key  hex字符串  只能16 或24字节
     * @param data 需要解密数据
     * @return hex字符串结果
     * @author lishaofeng  396191970@qq.com
     * @date: 2017年3月1日 上午10:45:54 <br/>
     */
    public static String decryptMode(String key, String data) {
        if (key.length() == 32) {
            key = key + key.substring(0, 16);
            System.out.println("after pad key:" + key);
        }
        byte[] encoded = null;
        encoded = decryptMode(TypeConvertUtil.str2Bcd(key), TypeConvertUtil.str2Bcd(data));
        String ret = TypeConvertUtil.bcd2Str(encoded);
        return ret;
    }

    public static String encryptPadFF(String key, String data) {
        data = StringUtils.rightPad(data, 32, 'f');
        System.out.println("after pad date " + data);
        return encryptMode(key, data);
    }

    public static String encryptIDCard(String key, String data) {
        data = data.toUpperCase();
        data = data.replace('X', 'D');

        data = StringUtils.rightPad(data, 32, 'f');
        System.out.println("after pad date " + data);
        return encryptMode(key, data);
    }

    /**
     * encryptMode:(方法的作用：). <br/>
     * TODO(注意事项 ：
     * hex字符串 举例“12323423” 函数内会进行转换为[0x12,0x32,0x34,0x23] 长度指转化后的长度).<br/>
     *
     * @param key  hex字符串  只能16 或24字节
     * @param data 需要加密数据
     * @return
     * @author lishaofeng  396191970@qq.com
     * @date: 2017年3月1日 上午10:46:59 <br/>
     */
    public static String encryptMode(String key, String data) {
        if (key.length() == 32) {
            key = key + key.substring(0, 16);
            System.out.println("after pad key:" + key);
        }
        byte[] encoded = null;
        encoded = encryptMode(TypeConvertUtil.str2Bcd(key), TypeConvertUtil.str2Bcd(data));
        String ret = TypeConvertUtil.bcd2Str(encoded);
        return ret;
    }
}