package com.lybank.util;

import java.util.SortedMap;

public class DecryEncfyUtil {
    public static String generateSign(SortedMap<String, String> params, String key) {
        StringBuffer sb = new StringBuffer();
        for (String keyItem : params.keySet()) {
            if (params.get(keyItem) != null && !params.get(keyItem).toString().equals("") && !keyItem.equals("sign")) {
                sb.append(keyItem + "=" + params.get(keyItem).toString() + "&");
            }
        }
        sb.append("key=" + key);
        String sign = null;
        sign = MD5Util.MD5Encode(sb.toString(), "UTF-8");//.getMD5(sb.toString().getBytes("UTF-8"));

        return sign;
    }
}
