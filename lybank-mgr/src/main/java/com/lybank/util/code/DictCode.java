package com.lybank.util.code;

import lombok.Data;

public @Data
class DictCode {


    public static final String DFLIMIT = "dfLimit";//代付限额
    public static final String LOGIN_GOOGLE_CODE = "login_google_code";//验证码是否开启,
    public static final String DF_GOOGLE_CODE = "df_google_code";//验证码是否开启,
    public static final String ENABLE = "enable";
    public static final String DISABLE = "disable";
    public static final String SCHEDULE_SWITCH = "schedule_switch";//定时器总开关
    public static final String CLEAR_SWITCH_DAY = "clear_switch_day ";//清除(第三方渠道、商户每天的字段数据)定时器总开关

}	