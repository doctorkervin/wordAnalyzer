package com.lybank.config.interceptor;

import com.lybank.entity.system.User;
import com.lybank.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 获取用户数据类
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {


    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userService.loadUserByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("用户：" + userName + "不存在！");
        }
        return new UserPrincipal(user);
    }

}
