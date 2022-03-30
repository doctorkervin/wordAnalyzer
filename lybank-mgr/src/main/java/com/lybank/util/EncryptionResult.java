package com.lybank.util;

public class EncryptionResult {
    private RSAReturnCode descData;// 状态/描述
    private String encryptData;// AES密钥加密请求报文
    private String encrtptKey;// RSA公钥加密AES密钥
    private String signData;// RSA密钥签名报文

    public RSAReturnCode getDescData() {
        return descData;
    }

    public void setDescData(RSAReturnCode descData) {
        this.descData = descData;
    }

    public String getEncryptData() {
        return encryptData;
    }

    public void setEncryptData(String encryptData) {
        this.encryptData = encryptData;
    }

    public String getEncrtptKey() {
        return encrtptKey;
    }

    public void setEncrtptKey(String encrtptKey) {
        this.encrtptKey = encrtptKey;
    }

    public String getSignData() {
        return signData;
    }

    public void setSignData(String signData) {
        this.signData = signData;
    }

    @Override
    public String toString() {
        return "EncryptionResult{" + "descData=" + descData + ", encryptData='" + encryptData + '\'' + ", encrtptKey='" + encrtptKey + '\'' + ", signData='" + signData + '\'' + '}';
    }

    public enum RSAReturnCode {
        /**
         * 成功
         */
        SUCCESS(20000, "成功"),
        /**
         * 加密失败
         */
        ENCRYPTIONFAILURE(20003, "加密失败"),
        /**
         * 验签失败
         */
        CHECKFAILURE(20002, "验签失败"),
        /**
         * 解密失败
         */
        DECRYPTIONFAILURE(20001, "解密失败");

        private final int code;
        private final String msg;

        RSAReturnCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        @Override
        public String toString() {
            return "returnCode{" + "code=" + code + ", msg='" + msg + '\'' + '}';
        }
    }
}
