package com.lybank.util;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * (C) Shanghai Sand Information Technology System Co., Ltd.
 * All Rights Reserved.
 * <p>
 * Description: 工具类 MD5Util
 * <p>
 * Modification History: <p>
 * ============================================================================= <p>
 * Author         Date          Modification Description <p>
 * ------------- ---------- --------------------------------------------------- <p>
 * Wu.WQ         2016/7/20         Create <p>
 * Wu.WQ         2016/10/15        切换使用apache的DigestUtils进行MD5加密 <p>
 * Wu.WQ         2017/3/28         提取apache-MD5加密代码  <p>
 * Wu.WQ         2017/5/16         增加过滤Map空字符的方法 <p>
 * ============================================================================= <p>
 */
public class MD5Util {

    private static final char[] DIGITS_LOWER;
    private static final char[] DIGITS_UPPER;

    static {
        DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        DIGITS_UPPER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    /**
     * MD5加密，生成签名  字符编码 默认为UTF-8
     *
     * @param params 封装参数到 map 对象
     * @param key    业务系统与支付网关的数据交互加密的
     * @return 返回签名
     */
    public static String sign(Map<String, Object> params, String key) {
        return sign(params, key, "UTF-8");
    }

    /**
     * MD5加密，生成签名
     *
     * @param params 封装参数到 map 对象
     * @param key    业务系统与支付网关的数据交互加密的
     * @param encode 字符编码
     * @return 返回签名
     */
    public static String sign(Map<String, Object> params, String key, String encode) {
        return MD5Encode(createLinkString(params, key), encode);
    }

    /**
     * 过滤空字符串和空值
     *
     * @param data 源data map
     * @return 过滤后的Map
     */
    public static Map<String, Object> filterBlank(Map<String, Object> data) {
        Map<String, Object> filterData = new HashMap<String, Object>();
        Set<String> keySet = data.keySet();
        for (String key : keySet) {
            Object value = data.get(key);
            if (value != null && isNotBlank(String.valueOf(value))) {
                // 对value值进行去除前后空处理
                filterData.put(key, value);
            }
        }
        return filterData;
    }

    /**
     * 生成待签名的字符串
     * 参数按照key=value的格式&连接，并按照参数名ASCII字典序排序
     *
     * @param params 封装参数到 map 对象
     * @param key    业务系统与支付网关的数据交互加密的
     * @return
     */
    public static String createLinkString(Map<String, Object> params, String key) {
        ArrayList<String> paramsKeys = new ArrayList<String>(params.keySet());
        //排序
        Collections.sort(paramsKeys);
        StringBuilder signTemp = new StringBuilder();
        for (String paramsKey : paramsKeys) {
            signTemp.append("&").append(paramsKey).append("=").append(params.get(paramsKey));
        }
        signTemp.append("&key=").append(key);
        return signTemp.subSequence(1, signTemp.length()).toString();
    }

    /**
     * 进行MD5加密 字符编码:UTF-8
     *
     * @param src 源字符串
     * @return 加密字符串
     */
    public static String MD5(String src) {
        return MD5Encode(src, "UTF-8");
    }


    /**
     * 进行MD5加密
     *
     * @param src    源字符串
     * @param encode 字符编码
     * @return 加密字符串
     */
    public static String MD5Encode(String src, String encode) {
        String result = "";
        if (src == null) {
            return result;
        }
        try {
            byte[] strTemp = src.getBytes(encode);
            result = md5Hex(strTemp);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /////////////////////////////////////////////////////////////////////////////////////

    public static String md5Hex(byte[] data) {
        return encodeHexString(md5(data));
    }

    private static byte[] md5(byte[] data) {
        try {
            return MessageDigest.getInstance("MD5").digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static String encodeHexString(byte[] data) {
        return new String(encodeHex(data));
    }

    private static char[] encodeHex(byte[] data) {
        return encodeHex(data, true);
    }

    private static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    private static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;

        for (int var5 = 0; i < l; ++i) {
            out[var5++] = toDigits[(240 & data[i]) >>> 4];
            out[var5++] = toDigits[15 & data[i]];
        }
        return out;
    }

    /////////////////////////////////////////////////////////////////////////////////////

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * Unix时间戳
     *
     * @return 自1970年1月1日 0点0分0秒以来的毫秒数
     */
    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }


    /**
     * 生成待签名的字符串
     * 参数按照key=value的格式&连接，并按照参数名ASCII字典序排序
     *
     * @param params 封装参数到 map 对象
     * @param key    业务系统与支付网关的数据交互加密的
     * @return
     */
    public static String createString(Map<String, String> params, String key) {
        ArrayList<String> paramsKeys = new ArrayList<String>(params.keySet());
        //排序
        Collections.sort(paramsKeys);
        StringBuilder signTemp = new StringBuilder();
        for (String paramsKey : paramsKeys) {
            signTemp.append("&").append(paramsKey).append("=").append(params.get(paramsKey));
        }
        signTemp.append("&key=").append(key);
        return signTemp.subSequence(1, signTemp.length()).toString();
    }

    /**
     * 生成待签名的字符串
     * 参数按照key=value的格式&连接，并按照参数名ASCII字典序排序
     *
     * @param params 封装参数到 map 对象
     * @param key    业务系统与支付网关的数据交互加密的
     * @return
     */
    public static String createString(Map<String, String> params, String key, String keyName) {
        ArrayList<String> paramsKeys = new ArrayList<String>(params.keySet());
        //排序
        Collections.sort(paramsKeys);
        StringBuilder signTemp = new StringBuilder();
        for (String paramsKey : paramsKeys) {
            signTemp.append("&").append(paramsKey).append("=").append(params.get(paramsKey));
        }
        signTemp.append("&" + keyName).append(key);
        return signTemp.subSequence(1, signTemp.length()).toString();
    }

    /**
     * 易收款MD5
     *
     * @param paramMap
     * @param key
     * @return
     */
    public static String MD5(Map<String, Object> paramMap, String key) {
        // TODO Auto-generated method stub
        SortedMap<String, Object> smap = new TreeMap<String, Object>(paramMap);
        String signStr = key + JSONObject.toJSONString(paramMap);
        // String signStr = JSONObject.fromObject(smap).toString();
        System.out.println("MD5原文:" + signStr);
        String sign = MD5Encode(signStr, "UTF-8");
        System.out.println("MD5值：" + sign);
        return sign;
    }

    /**
     * 乐刷MD5
     *
     * @param paramMap
     * @param key      秘钥
     * @param keyName  秘钥key别名
     * @return
     */
    public static String MD5(Map<String, String> paramMap, String key, String keyName) {
        String signStr = createString(paramMap, key, keyName);
        System.out.println("MD5原文:" + signStr);
        String sign = MD5Encode(signStr, "UTF-8");
        System.out.println("MD5值：" + sign);
        return sign;
    }
}
