package com.lybank.config.interceptor;

import com.lybank.exception.GoogleCodeException;
import com.lybank.exception.ValidateCodeException;
import com.lybank.service.system.SystemLogService;
import com.lybank.util.Constant;
import com.lybank.util.IpUtil;
import com.lybank.util.Result;
import com.lybank.util.code.SystemLogCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录验证失败工具类
 */
@Service
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Autowired
    private SystemLogService systemLogService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String userName = ServletRequestUtils.getStringParameter(httpServletRequest, Constant.USER_NAME);
        Result r = new Result();
        r.setCode(Constant.ResultStatus_FAIL);
        String IP = IpUtil.getIpAddr(httpServletRequest);
        if (e instanceof GoogleCodeException) {
            r.setCode(Constant.ResultStatus_VERIFY);
            systemLogService.insertSystemLog(userName, IP, SystemLogCode.GCODE_ERROR, e.getMessage());
        }
        if (e instanceof ValidateCodeException) {
            systemLogService.insertSystemLog(userName, IP, SystemLogCode.CODE_ERROR, e.getMessage());
        }
        if (e instanceof BadCredentialsException) {
            systemLogService.insertSystemLog(userName, IP, SystemLogCode.PASSWORD_OR_USERNAME_ERROR, e.getMessage());
        }

        r.setMsg(e.getMessage());
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        out.write(r.toJsonString());
        out.flush();
        out.close();
    }


}
