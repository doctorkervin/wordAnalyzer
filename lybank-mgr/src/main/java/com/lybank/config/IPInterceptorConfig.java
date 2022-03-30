package com.lybank.config;

import com.lybank.common.util.NumeralUtil;
import com.lybank.entity.system.WhitelistIp;
import com.lybank.service.system.WhitelistIpService;
import com.lybank.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ABu
 * @ClassName com.lybank.config
 * @Description IP拦截器
 * @Date 2019/7/29 11:11
 * @Version 1.0
 */
@Configuration
public class IPInterceptorConfig implements HandlerInterceptor {
    @Autowired
    private WhitelistIpService whitelistIpService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IpUtil.getIpAddr(request);
        System.out.println(request.getRequestURI());
        System.out.println("IP=preHandle:" + ip);
        WhitelistIp p = whitelistIpService.findByIpAndType(ip, NumeralUtil.POSITIVE_ONE);
        if (p == null || p.getIsEnable() == NumeralUtil.POSITIVE_TWO) {
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("IP=postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("IP=afterCompletion");
    }
}
