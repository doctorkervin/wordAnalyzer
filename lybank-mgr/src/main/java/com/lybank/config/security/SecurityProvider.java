package com.lybank.config.security;

import com.lybank.config.interceptor.SpringDataUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义认证服务,检查登录验证账户是否出问题
 */
@Service("securityProvider")
public class SecurityProvider implements AuthenticationProvider {

    @Autowired
    private SpringDataUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authenticate) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token
                = (UsernamePasswordAuthenticationToken) authenticate;
        String username = token.getName();
        UserDetails userDetails = null;

        if (username != null) {
            userDetails = userDetailsService.loadUserByUsername(username);
        }
        System.out.println("$$" + userDetails);

        if (userDetails == null) {
            throw new UsernameNotFoundException("用户名/密码无效");
        }

        if (!userDetails.isEnabled()) {
            System.out.println("jinyong用户已被禁用");
            throw new DisabledException("jinyong用户已被禁用");
        } else if (!userDetails.isAccountNonExpired()) {
            System.out.println("guoqi账号已过期");
            throw new AccountExpiredException("账号已过期");
        } else if (!userDetails.isAccountNonLocked()) {
            System.out.println("suoding账号已被锁定");
            throw new LockedException("账号已被锁定");
        } else if (!userDetails.isCredentialsNonExpired()) {
            System.out.println("凭证已过期");
            throw new CredentialsExpiredException("凭证已过期");
        }
        //验证码校验
        String password = userDetails.getPassword();
        //与authentication里面的credentials相比较
        if (!password.equals(token.getCredentials())) {
            throw new BadCredentialsException("用户名和密码不正确");
        }
        //授权  
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
