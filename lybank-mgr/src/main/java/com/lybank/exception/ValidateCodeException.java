package com.lybank.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 *
 * @author xiaoqi
 * 2018年10月5日
 */
public class ValidateCodeException extends AuthenticationException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
