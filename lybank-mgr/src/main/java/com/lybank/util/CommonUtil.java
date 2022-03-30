package com.lybank.util;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;


public final class CommonUtil {

    /**
     * @param  length
     * @return 指定的字符串长度
     * @生成指定长度的随机字符串
     */
    public static String generateLenString(int length) {
        char[] cResult = new char[length];
        int[] flag = {0, 0, 0}; // A-Z, a-z, 0-9
        int i = 0;
        while (flag[0] == 0 || flag[1] == 0 || flag[2] == 0 || i < length) {
            i = i % length;
            int f = (int) (Math.random() * 3 % 3);
            if (f == 0)
                cResult[i] = (char) ('A' + Math.random() * 26);
            else if (f == 1)
                cResult[i] = (char) ('a' + Math.random() * 26);
            else
                cResult[i] = (char) ('0' + Math.random() * 10);
            flag[f] = 1;
            i++;
        }
        return new String(cResult);
    }

    /**
     * @param str
     * @return
     * @空判
     */
    public static boolean isnull(String str) {
        if (null == str || str.equalsIgnoreCase("null") || str.trim().equals("")) {
            return true;
        } else
            return false;
    }

    /**
     * @时间格式为yyyyMMddHHmmss
     */
    public static String reqDate() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * @throws ParseException
     * @时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static String resDate(String resdate) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new SimpleDateFormat("yyyyMMddHHmmss").parse(resdate));
    }

    /**
     * 生成订单
     *
     * @return
     */
    public static String GetOutTradeNo() {
        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字下划线
        // 保证商户系统端不能重复，建议通过数据库sequence生成
        return "2017" + System.currentTimeMillis() + (int) (Math.random() * 100);
    }

    /**
     * 生成id
     *
     * @return
     */
    public static String MerchantId() {
        // (必填) 商户网站订单系统中唯�?订单号，64个字符以内，只能包含字母、数字�?�下划线�?
        // �?保证商户系统端不能重复，建议通过数据库sequence生成�?
        return "2017" + System.currentTimeMillis() + (int) (Math.random() * 100);
    }

    /**
     * 全球唯一id
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        String a = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
        return a;
    }

    /**
     * 当前时间   格式YYYY-MM-dd HH:mm:ss
     */
    public static String getData() {
        return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }

    /**
     * 代理商编号
     */
    public static String getDaiLiMerchantCodes() {
        return "20" + new SimpleDateFormat("YYYYMMddHHmmss").format(System.currentTimeMillis());
    }

    /**
     * 印证时间
     *
     * @param patternString
     * @return
     */
    public static boolean isTimeLegal(String patternString) {
        if (patternString.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 组装请求，返回报文字符串用于显示
     *
     * @param data
     * @return
     */
    public static String genHtmlResult(Map<String, String> data) {

        TreeMap<String, String> tree = new TreeMap<String, String>();
        Iterator<Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> en = it.next();
            tree.put(en.getKey(), en.getValue());
        }
        it = tree.entrySet().iterator();
        StringBuffer sf = new StringBuffer();
        while (it.hasNext()) {
            Entry<String, String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            if ("respCode".equals(key)) {
                sf.append("<b>" + key + "=" + value + "</br></b>");
            } else
                sf.append(key + "=" + value + "</br>");
        }
        return sf.toString();
    }

    public static String getRequestData(HttpServletRequest request) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        InputStream inputStream = null;
        String message;
        try {
            inputStream = request.getInputStream();
            int c;
            while ((c = inputStream.read()) != -1) {
                os.write(c);
            }
            message = new String(os.toByteArray());
        } catch (IOException e) {
            //logger.error(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("读取数据流失败");
        } finally {
            os.close();
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return message;
    }
}