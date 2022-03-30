package com.lybank.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 *
 * @author xiaoqi
 * 2018年10月5日
 */
public class GoogleCodeException extends AuthenticationException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GoogleCodeException(String msg) {
        super(msg);
    }

}
