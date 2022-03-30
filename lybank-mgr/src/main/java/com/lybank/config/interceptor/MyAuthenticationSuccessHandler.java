package com.lybank.config.interceptor;

import com.lybank.service.system.SystemLogService;
import com.lybank.util.Constant;
import com.lybank.util.IpUtil;
import com.lybank.util.Result;
import com.lybank.util.code.SystemLogCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功工具类
 */
@Service
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private SystemLogService systemLogService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // ObjectMapper objectMapper = new ObjectMapper();
        String IP = IpUtil.getIpAddr(request);
        String userName = ServletRequestUtils.getStringParameter(request, Constant.USER_NAME);
        systemLogService.insertSystemLog(userName, IP, SystemLogCode.LOGIN, "登录成功");
        Result r = new Result();
        r.setCode(Constant.ResultStatus_SUCCESS);
        r.setMsg(authentication.getName() + "登录成功！");
        r.setData(authentication.getDetails());
        // String s = "{\"status\":\"success\",\"msg\":" + objectMapper.writeValueAsString(authentication.getPrincipal()) + "}";
        out.write(r.toJsonString());
        out.flush();
        out.close();

    }

}
