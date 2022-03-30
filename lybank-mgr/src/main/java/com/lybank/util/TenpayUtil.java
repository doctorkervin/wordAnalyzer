package com.lybank.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


public class TenpayUtil {

    private static Object Server;
    private static String QRfromGoogle;

    /**
     * 把对象转换成字符
     *
     * @param obj
     * @return String 转换成字符串,若对象为null,则返回空字符
     */
    public static String toString(Object obj) {
        if (obj == null)
            return "";

        return obj.toString();
    }

    /**
     * 把对象转换为int数
     *
     * @param obj 包含数字的对
     * @return int 转换后的数,对不能转换的对象返回
     */
    public static int toInt(Object obj) {
        int a = 0;
        try {
            if (obj != null)
                a = Integer.parseInt(obj.toString());
        } catch (Exception e) {

        }
        return a;
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     *
     * @return String
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    /**
     * 获取当前日期 yyyyMMdd
     *
     * @param date
     * @return String
     */
    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate = formatter.format(date);
        return strDate;
    }

    /**
     * 取出指定长度大小的随机正整数.
     *
     * @param length int 设定出随机数的长度length小于11
     * @return int 返回生成的随机数
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 获取编码字符
     *
     * @param request
     * @param response
     * @return String
     */

    public static String getCharacterEncoding(HttpServletRequest request,
                                              HttpServletResponse response) {

        if (null == request || null == response) {
            return "gbk";
        }

        String enc = request.getCharacterEncoding();
        if (null == enc || "".equals(enc)) {
            enc = response.getCharacterEncoding();
        }

        if (null == enc || "".equals(enc)) {
            enc = "gbk";
        }

        return enc;
    }

    public static String URLencode(String content) {

        String URLencode;

        URLencode = replace(Server.equals(content), "+", "%20");

        return URLencode;
    }

    private static String replace(boolean equals, String string, String string2) {

        return null;
    }

    /**
     * 获取unix时间，从1970-01-01 00:00:00的秒
     *
     * @param date
     * @return long
     */
    public static long getUnixTime(Date date) {
        if (null == date) {
            return 0;
        }

        return date.getTime() / 1000;
    }

    public static String QRfromGoogle(String chl) {
        int widhtHeight = 300;
        String EC_level = "L";
        int margin = 0;
        String QRfromGoogle;
        chl = URLencode(chl);

        QRfromGoogle = "http://chart.apis.google.com/chart?chs=" + widhtHeight + "x" + widhtHeight + "&cht=qr&chld=" + EC_level + "|" + margin + "&chl=" + chl;

        return QRfromGoogle;
    }

    /**
     * 时间转换成字符串
     *
     * @param date       时间
     * @param formatType 格式化类
     * @return String
     */
    public static String date2String(Date date, String formatType) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        return sdf.format(date);
    }

    /* 获取随机字符*/
    public static String getNonceStr() {
        // 随机
        String currTime = TenpayUtil.getCurrTime();
        // 8位日
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机
        String strRandom = TenpayUtil.buildRandom(4) + "";
        //10位序列号,可以自行调整
        return strTime + strRandom;
    }

    public static String getMoney(String amount) {
        if (amount == null) {
            return "";
        }
        // 金额转化为分为单�?
        String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含,
        // 或的金
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
                    ".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
                    ".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
                    ".", "") + "00");
        }
        return amLong.toString();
    }

    public static Map<String, String> xmlStrToMap(String inputString) {
        Document doc = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            doc = DocumentHelper.parseText(inputString);
            Element rootElt = doc.getRootElement(); // 获取根节
            Iterator iter = rootElt.elementIterator();// 将数据转换为迭代模式
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                map.put(recordEle.getName(), recordEle.getTextTrim());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String, String> toMap(String str) {
        Map<String, String> resMap = new HashMap<String, String>();
        String[] resStrs = str.split("&");
        String key = null, value = null;
        int index = 0;
        for (String string : resStrs) {
//				String[] keyVals=string.split("=");
            index = string.indexOf("=");
            key = string.substring(0, index);
            value = string.substring(index + 1);
            resMap.put(key, value);
        }
        return resMap;
    }

    public static String createRetStr(Map<String, String> packageParams) {
        StringBuffer sb = new StringBuffer();
        Set<?> es = packageParams.entrySet();
        Iterator<?> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = String.valueOf(entry.getValue());
            if (null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }
        String sss = sb.toString().substring(sb.toString().length() - 1, sb.toString().length());
        if (sss.equals("&")) {
            sss = sb.toString().substring(0, sb.toString().length() - 1);
        }
        return sss;

    }


    //

}
	
	










