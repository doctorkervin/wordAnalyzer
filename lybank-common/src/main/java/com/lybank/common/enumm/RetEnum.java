package com.lybank.common.enumm;

/**
 * RPC调用返回码枚举类
 * 对应方法调用返回值中的rpcRetCode和rpcRetMsg
 * Created by admin on 2016/4/27.
 */
public enum RetEnum {

    // 0000: 成功
    RET_SUCCESS("0000", ""),

    // 接口业务相关
    FAIL_ONE_THOUSAND("1000", "参数对象为空"),
    FAIL_ONE_THOUSAND_AND_ONE("1001", "商户不存在"),
    FAIL_ONE_THOUSAND_AND_TWO("1002", "代码错误"),
    FAIL_ONE_THOUSAND_AND_THREE("1003", "订单存储失败"),
    FAIL_ONE_THOUSAND_AND_FOUR("1004", "查询商户重复订单错误"),
    FAIL_ONE_THOUSAND_AND_FIVE("1005", "商户重复下单"),
    FAIL_ONE_THOUSAND_AND_SIX("1006", "单笔超额"),
    FAIL_ONE_THOUSAND_AND_SEVEN("1007", "商户每日限额已满"),
    FAIL_ONE_THOUSAND_AND_EIGHT("1008", "商户总限额已满"),
    FAIL_ONE_THOUSAND_AND_NINE("1009", "银行卡不存在"),
    FAIL_ONE_THOUSAND_AND_TEN("1010", "银行卡限额或次数超限"),
    FAIL_ONE_THOUSAND_AND_ELEVEN("1011", ""),




    // 未知错误
    RET_UNKNOWN_ERROR("9999", "未知错误");

    private String code;
    private String message;

    private RetEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static RetEnum getRetEnum(String code) {
        if (code == null) {
            return null;
        }

        RetEnum[] values = RetEnum.values();
        for (RetEnum e : values) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
