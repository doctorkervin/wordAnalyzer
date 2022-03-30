package com.lybank.util;

public class Constant {
    public static final String USER_NAME = "username";//谷歌验证码状态
    public static final String GOOGLE_CODE = "googleCode";//谷歌验证码状态
    public static final String VERIFY_CODE = "verifyCode";//验证码
    public static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";//验证码在session中的key
    public static final Integer EXPIRED_TIME = 60;//验证码超时时间
    public static final String VERIFY_URL = "/login";//,号分隔

    /**
     * 用户状态
     *****/
    public static final Integer USER_LOCKED = 1;
    public static final Integer USER_UNLOCKED = 0;
    public static final Integer USER_ENABLED = 1;
    public static final Integer USER_DISABLE = 0;
    public static final Integer USER_NON_EXPIRED = 0;
    public static final Integer USER_EXPIRED = 1;
    public static final Integer USER_CREDENTIALSEXPIRED = 1;
    public static final Integer USER_NON_CREDENTIALSEXPIRED = 0;

    public static final String ResultStatus_SUCCESS = "success";
    public static final String ResultStatus_FAIL = "fail";
    public static final String ResultStatus_ERROR = "error";
    public static final String ResultStatus_EXCEPTION = "exception";
    public static final String ResultStatus_WARN = "warn";
    public static final String ResultStatus_TRUE = "true";
    public static final String ResultStatus_FALSE = "false";
    public static final String ResultStatus_VERIFY = "verify";
    public static final String ResultStatus_SUCCESS_MSG = "操作成功";
    public static final String ResultStatus_FAIL_MSG = "操作失败";
    public static final String ResultStatus_SUCCESS_CODE = "200";
    public static final String ResultStatus_FAIL_CODE = "0";
    public static final String Regular_Regex = "1";
    public static final String Regular_Nature = "2";
    public static final String Regular_Likely = "3";
    public static final String Nature_Place = "ns";
    public static final String Nature_ChineseName = "nr";
    public static final String Nature_JapaneseName = "nrj";
    public static final String Nature_JiGou = "nt";
    public static final String Task_Secret = "3";
    public static final String Task_upload_Scan = "1";
    public static final String Task_Path_Scan = "2";
    public static final String Secret_File_Extension = "jks,ks,jce,p12,pfx,bks,ubr,cer,crt,rsa,p7b,p7r,p7c,p7m,p7s,pem,p10,csr,pvk,spc,csr,key,keystore,pkcs12,pfx,p12,der,cert,crt,,cer,crl";



}
