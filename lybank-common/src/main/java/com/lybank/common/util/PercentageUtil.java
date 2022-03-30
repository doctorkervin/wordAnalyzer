package com.lybank.common.util;

import java.text.NumberFormat;

/**
 * @Author 阿布
 * @ClassName com.lybank.common.util.PercentageUtil
 * @Description 百分比计算工具类、
 * @Date 2019-01-30
 * @Version 1.0
 */
public class PercentageUtil {
    /**
     * 求百分比公共方法
     *
     * @param beExceptNumber 除数,如数字10,10是总数，4+6=10，然后总数
     * @param exceptNumber   被除数,如数字4，用于除于总数得到当前数所占百分比(如:4/10 * 100)
     * @return
     */
    public static String getPercentage(int beExceptNumber, int exceptNumber) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        float f =  ((float)exceptNumber / (float)beExceptNumber) * 100;
        String result = numberFormat.format(f);
        return result;
    }
}
