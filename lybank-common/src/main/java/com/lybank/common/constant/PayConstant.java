package com.lybank.common.constant;

import java.io.File;

/**
 * @author dingzhiwei jmdhappy@126.com
 * @version V1.0
 * @Description: 支付常量类
 * @date 2017-07-05
 * @Copyright: www.lypay.org
 */
public class PayConstant {

	public final static Integer CHANNEL_MERCH_START=1;//第三方渠道商户，启用
	public final static Integer CHANNEL_MERCH_STOP=0;////第三方渠道商户，停用
	public final static Integer PAY_CHANNEL_START=1;//第三方渠道关系 ，启用
	public final static Integer PAY_CHANNEL_STOP=0;////第三方渠道关系，停用
    public final static String PAY_CHANNEL_WX_JSAPI = "WX_JSAPI";                // 微信公众号支付
    public final static String PAY_CHANNEL_WX_NATIVE = "WX_NATIVE";                // 微信原生扫码支付
    public final static String PAY_CHANNEL_WX_APP = "WX_APP";                    // 微信APP支付
    public final static String PAY_CHANNEL_WX_MWEB = "WX_MWEB";                    // 微信H5支付
    public final static String PAY_CHANNEL_IAP = "IAP";                            // 苹果应用内支付
    public final static String PAY_CHANNEL_ALIPAY_MOBILE = "ALIPAY_MOBILE";        // 支付宝移动支付
    public final static String PAY_CHANNEL_ALIPAY_PC = "ALIPAY_PC";                // 支付宝PC支付
    public final static String PAY_CHANNEL_ALIPAY_WAP = "ALIPAY_WAP";            // 支付宝WAP支付
    public final static String PAY_CHANNEL_ALIPAY_QR = "ALIPAY_QR";                // 支付宝当面付之扫码支付

    public final static String CHANNEL_NAME_WX = "WX";                // 渠道名称:微信
    public final static String CHANNEL_NAME_ALIPAY = "ALIPAY";        // 渠道名称:支付宝
    public final static String CHANNEL_NAME_JD="JD";//渠道名称:京东
    public final static String CHANNEL_NAME_OTHER="OTHER";//渠道名称:其他第四方


    public final static int PAY_STATUS_LIMITED = -3;    //订单金额超限，单笔超限或总额超限
    public final static int PAY_STATUS_EXPIRED = -2;    // 订单过期
    public final static int PAY_STATUS_FAILED = -1;    // 支付失败
    public final static int PAY_STATUS_INIT = 0;        // 初始态
    public final static int PAY_STATUS_PAYING = 1;    // 支付中
    public final static int PAY_STATUS_SUCCESS = 2;    // 支付成功
    public final static int PAY_STATUS_COMPLETE = 3;    // 业务完成

    public final static int TRANS_STATUS_INIT = 0;        // 初始态
    public final static int TRANS_STATUS_TRANING = 1;        // 转账中
    public final static int TRANS_STATUS_SUCCESS = 2;        // 成功
    public final static int TRANS_STATUS_FAIL = 3;        // 失败
    public final static int TRANS_STATUS_COMPLETE = 4;    // 业务完成

    public final static int TRANS_RESULT_INIT = 0;        // 不确认结果
    public final static int TRANS_RESULT_REFUNDING = 1;    // 等待手动处理
    public final static int TRANS_RESULT_SUCCESS = 2;        // 确认成功
    public final static int TRANS_RESULT_FAIL = 3;        // 确认失败

    public final static int REFUND_STATUS_INIT = 0;        // 初始态
    public final static int REFUND_STATUS_REFUNDING = 1;    // 转账中
    public final static int REFUND_STATUS_SUCCESS = 2;    // 成功
    public final static int REFUND_STATUS_FAIL = 3;        // 失败
    public final static int REFUND_STATUS_COMPLETE = 4;    // 业务完成

    public final static int REFUND_RESULT_INIT = 0;        // 不确认结果
    public final static int REFUND_RESULT_REFUNDING = 1;    // 等待手动处理
    public final static int REFUND_RESULT_SUCCESS = 2;    // 确认成功
    public final static int REFUND_RESULT_FAIL = 3;        // 确认失败

    public final static int MCH_NOTIFY_TYPE_PAY = 1;        // 商户通知类型:支付订单
    public final static int MCH_NOTIFY_TYPE_TRANS = 2;        // 商户通知类型:转账订单
    public final static int MCH_NOTIFY_TYPE_REFUND = 3;    // 商户通知类型:退款订单

    public final static int MCH_NOTIFY_STATUS_NOTIFYING = 1;    // 通知中
    public final static int MCH_NOTIFY_STATUS_SUCCESS = 2;        // 通知成功
    public final static int MCH_NOTIFY_STATUS_FAIL = 3;        // 通知失败


    public final static String RESP_UTF8 = "UTF-8";            // 通知业务系统使用的编码

    public static final String RETURN_PARAM_RETCODE = "retCode";
    public static final String RETURN_PARAM_RETMSG = "retMsg";
    public static final String RESULT_PARAM_RESCODE = "resCode";
    public static final String RESULT_PARAM_ERRCODE = "errCode";
    public static final String RESULT_PARAM_ERRCODEDES = "errCodeDes";
    public static final String RESULT_PARAM_SIGN = "sign";

    public static final String RETURN_VALUE_SUCCESS = "SUCCESS";
    public static final String RETURN_VALUE_FAIL = "FAIL";

    public static final String RETURN_ALIPAY_VALUE_SUCCESS = "success";
    public static final String RETURN_ALIPAY_VALUE_FAIL = "fail";
    public static final String NOTIFY_BUSI_PAY = "NOTIFY_VV_PAY_RES";
    public static final String NOTIFY_BUSI_TRANS = "NOTIFY_VV_TRANS_RES";

    public static class JdConstant {
        public final static String CONFIG_PATH = "jd" + File.separator + "jd";    // 京东支付配置文件路径
    }

    public static class WxConstant {
        public final static String TRADE_TYPE_APP = "APP";                                    // APP支付
        public final static String TRADE_TYPE_JSPAI = "JSAPI";                                // 公众号支付或小程序支付
        public final static String TRADE_TYPE_NATIVE = "NATIVE";                            // 原生扫码支付
        public final static String TRADE_TYPE_MWEB = "MWEB";                                // H5支付
        public final static String TRADE_STATUS_SUCCESS="SUCCESS";//成功
        public final static String TRADE_STATUS_FAIL="FAIL";//失败
        
    }

    public static class IapConstant {
        public final static String CONFIG_PATH = "iap" + File.separator + "iap";        // 苹果应用内支付
    }

    public static class AlipayConstant {
        public final static String CONFIG_PATH = "alipay" + File.separator + "alipay";    // 支付宝移动支付
        public final static String TRADE_STATUS_WAIT = "WAIT_BUYER_PAY";        // 交易创建,等待买家付款
        public final static String TRADE_STATUS_CLOSED = "TRADE_CLOSED";        // 交易关闭
        public final static String TRADE_STATUS_SUCCESS = "TRADE_SUCCESS";        // 交易成功
        public final static String TRADE_STATUS_FINISHED = "TRADE_FINISHED";    // 交易成功且结束
    }

}
