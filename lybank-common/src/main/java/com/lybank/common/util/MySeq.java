package com.lybank.common.util;


import java.util.concurrent.atomic.AtomicLong;

/**
 * @author dingzhiwei jmdhappy@126.com
 * @version V1.0
 * @Description: 生成全局唯一序列号工具类
 * @date 2017-07-05
 * @Copyright: www.lypay.org
 */
public class MySeq {

    private static AtomicLong PAY_SEQ = new AtomicLong(0L);
    private static String PAY_SEQ_PREFIX = "P";//支付订单号
    private static AtomicLong TRANS_SEQ = new AtomicLong(0L);
    private static String TRANS_SEQ_PREFIX = "T";//事务序列前缀
    private static AtomicLong REFUND_SEQ = new AtomicLong(0L);
    private static String REFUND_SEQ_PREFIX = "R";//退款序列前缀
    private static AtomicLong MCH_INFO_SEQ = new AtomicLong(0L);
    private static String MCH_INFO_SEQ_PREFIX = "MI";//商户信息序列前缀
    private static AtomicLong BANK_CARD_INFO_SEQ = new AtomicLong(0L);
    private static String BANK_CARD_INFO_SEQ_PREFIX = "B";



    private static String node = "00";

    static {
        try {
            //URL url = Thread.currentThread().getContextClassLoader().getResource("config" + File.separator + "system.properties");
            //Properties properties = new Properties();
            //properties.load(url.openStream());
            //node = properties.getProperty(ConfigEnum.SERVER_NAME.getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPay() {
        return getSeq(PAY_SEQ_PREFIX, PAY_SEQ);
    }

    public static String getTrans() {
        return getSeq(TRANS_SEQ_PREFIX, TRANS_SEQ);
    }

    public static String getRefund() {
        return getSeq(REFUND_SEQ_PREFIX, REFUND_SEQ);
    }

    public static String getMerchantInfo() {
        return getSeq(MCH_INFO_SEQ_PREFIX, MCH_INFO_SEQ);
    }

    public static String getBankCardNo(){return getSeqBank(BANK_CARD_INFO_SEQ_PREFIX, BANK_CARD_INFO_SEQ);}

    private static String getSeqBank(String prefix, AtomicLong seq) {
        prefix += node;
        return String.format("%s%s%06d", prefix, System.currentTimeMillis(), (int) seq.getAndIncrement() % 1000000);
    }

    private static String getSeq(String prefix, AtomicLong seq) {
        prefix += node;
        return String.format("%s%s%06d", prefix, DateUtil.getSeqString(), (int) seq.getAndIncrement() % 1000000);
    }

    public static void main(String[] args) {
//        System.out.println("pay=" + getPay());
//        System.out.println("trans=" + getTrans());
//        System.out.println("refund=" + getRefund());
        System.out.println("mchInfo=" + getMerchantInfo().length());

    }

}