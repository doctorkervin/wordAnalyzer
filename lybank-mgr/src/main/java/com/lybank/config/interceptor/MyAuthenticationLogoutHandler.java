package com.lybank.config.interceptor;

import com.lybank.service.system.SystemLogService;
import com.lybank.util.IpUtil;
import com.lybank.util.code.SystemLogCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录退出工具类
 */
@Service
public class MyAuthenticationLogoutHandler implements LogoutSuccessHandler {
    @Autowired
    private SystemLogService systemLogService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        UserPrincipal userPrincipal = null;
        if (authentication.getPrincipal() instanceof UserPrincipal) {
            userPrincipal = (UserPrincipal) authentication.getPrincipal();

        }
        String IP = IpUtil.getIpAddr(request);
//        String userName = ServletRequestUtils.getStringParameter(request, Constant.USER_NAME);
        String userName = userPrincipal != null ? userPrincipal.getUser().getLoginName() : null;
        systemLogService.insertSystemLog(userName, IP, SystemLogCode.LOGOUT, "登出成功");
        response.sendRedirect("/");

    }

}
