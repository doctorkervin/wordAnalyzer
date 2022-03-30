package com.lybank.common.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author dingzhiwei jmdhappy@126.com
 * @version V1.0
 * @Description: 日期时间工具类
 * @date 2017-07-05
 * @Copyright: www.lypay.org
 */
public class DateUtil {

    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd HH:mm:ss.S";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddhhmmssSSS";
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddhhmmss";
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_YYYYMMDD_OBLIQUE_LINE = "yyyy/MM/dd";
    public static final String FORMAT_YYYYMMDDHHMMSS_OBLIQUE_LINE = "yyyy/MM/dd HH:mm:ss";
    public static final String FORMAT_HHMMSS = "hhmmss";
    public static final String FORMAT_HHMMSS_COLON = "hh:mm:ss";
    public static final String DATE_TO_STRING_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String STRING_START_TIME = "00:00:00";
    public static final String STRING_END_TIME = "23:59:59";

    public static String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_YYYYMMDDHHMMSS);
        return format.format(new Date());
    }

    public static String getSeqString() {
        SimpleDateFormat fm = new SimpleDateFormat(FORMAT_YYYYMMDDHHMMSS); // "yyyyMMdd G
        return fm.format(new Date());
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前时间，格式为 yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentTimeStr(String format) {
        format = StringUtils.isBlank(format) ? FORMAT_YYYY_MM_DD_HH_MM_SS : format;
        Date now = new Date();
        return date2Str(now, format);
    }

    public static String date2Str(Date date) {
        return date2Str(date, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 时间转换成 Date 类型
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if ((format == null) || format.equals("")) {
            format = FORMAT_YYYY_MM_DD_HH_MM_SS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            return sdf.format(date);
        }
        return "";
    }

    /**
     * 字符串转日期时间
     *
     * @param date
     * @param format
     * @return
     */
    public static Date getStringToDate(String date, String format) {
        if ((format == null) || format.equals("")) {
            format = FORMAT_YYYY_MM_DD_HH_MM_SS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            try {
                return sdf.parse(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取批量付款预约时间
     *
     * @return
     */
    public static String getRevTime() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        String dateString = new SimpleDateFormat(DateUtil.FORMAT_YYYYMMDDHHMMSS).format(cal.getTime());
        System.out.println(dateString);
        return dateString;
    }

    /**
     * 时间比较
     *
     * @param date1
     * @param date2
     * @return DATE1>DATE2返回1，DATE1<DATE2返回-1,等于返回0
     */
    public static int compareDate(String date1, String date2, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 把给定的时间减掉给定的分钟数
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date minusDateByMinute(Date date, int minute) {
        Date newDate = new Date(date.getTime() - (minute * 60 * 1000));
        return newDate;
    }

    /**
     * 计算2个日期时间相差几分钟
     *
     * @param startDate 开始日期时间
     * @param endDate   结束日期时间
     * @return
     */
    public static int getMinute(Date startDate, Date endDate) {
        Long between = (endDate.getTime() - startDate.getTime()) / 60000;
        return between != null ? between.intValue() : 0;
    }

    /**
     * 计算2个日期时间相差几秒
     *
     * @param startDate 开始日期时间
     * @param endDate   结束日期时间
     * @return
     */
    public static int getSecond(Date startDate, Date endDate) {
        Long between = (endDate.getTime() - startDate.getTime()) / 1000;
        return between != null ? between.intValue() : 0;
    }
}
