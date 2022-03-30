package com.lybank.common.util;

import java.math.BigDecimal;

/**
 * @author: dingzhiwei
 * @date: 17/11/1
 * @description:
 */
public class StrUtil {

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String toString(Object obj, String nullStr) {
        return obj == null ? nullStr : obj.toString();
    }
    
    /**
     * 分转元，转换为bigDecimal在toString
     * @return
     */
    public static String Fen2Yuan(String price) {
        return BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(100)).toString();
    }
	/**
	 * 元转分方法，返回分的字符串（只保留分的整数部分）
	 * 
	 * @param yuanStr
	 *            元字符串
	 * @return 分的字符串
	 * @throws Exception
	 */
	public static String yuan2fen(String yuanStr) throws Exception {
		BigDecimal temp = BigDecimal.valueOf(Double.valueOf(yuanStr));
		temp = temp.multiply(BigDecimal.valueOf(100)); // 将temp乘以100
		String fenStr = String.valueOf(temp.longValue());

		return fenStr;
	}

}
