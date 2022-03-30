package com.lybank.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class MyStringUtils {


    /**
     * 元转分方法，返回分的字符串（只保留分的整数部分）
     *
     * @param yuanStr 元字符串
     * @return 分的字符串
     * @throws Exception
     */
    public static String Yuan2Fen(String yuanStr) throws Exception {
        BigDecimal temp = BigDecimal.valueOf(Double.valueOf(yuanStr));
        temp = temp.multiply(BigDecimal.valueOf(100)); // 将temp乘以100
        String fenStr = String.valueOf(temp.longValue());

        return fenStr;
    }

    /**
     * 分转元，转换为bigDecimal在toString
     *
     * @return
     */
    public static String Fen2Yuan(String price) {
        return BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(100)).toString();
    }

    //生成订单 号
    public static String gennerateSerial() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        int random = (int) (Math.random() * 100000);
        return df.format(ca.getTime()) + random;
    }

    public static String getUUID32() {
        String tt = UUID.randomUUID().toString().replaceAll("-", "");
        return tt;
    }

    public void test() {
		/*System.out.println(getUUID());
		System.out.println(getUUID());
		System.out.println(getUUID());
		System.out.println(getUUID());
		System.out.println(gennerateSerial());
		System.out.println(gennerateSerial());
		System.out.println(gennerateSerial());
		System.out.println(gennerateSerial());
		System.out.println(gennerateSerial());
		System.out.println(gennerateSerial());*/
    }

}
