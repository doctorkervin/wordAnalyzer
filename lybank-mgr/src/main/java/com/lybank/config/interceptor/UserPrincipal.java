package com.lybank.config.interceptor;

import com.lybank.entity.system.User;
import com.lybank.util.Constant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户权限获取类
 */
public class UserPrincipal implements UserDetails {


    /**
     * 构造用户权限对象
     */
    private static final long serialVersionUID = 1L;
    private final User user;
    //private final String role;

    public UserPrincipal(User user) {
        this.user = user;
        //  this.role=user.getRole();
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        if (this.user.getRole() == null) return null;
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        GrantedAuthority role = new SimpleGrantedAuthority(user.getRole().getCode());
        roles.add(role);
        //List<Authority> authorities=this.user.getRole().getAuthorities();
        return roles;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user.getPwd();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user.getLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return user.getAccountExpired() == Constant.USER_NON_EXPIRED;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return user.getAccountLocked() == Constant.USER_UNLOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return user.getCredentialsExpired() == Constant.USER_NON_CREDENTIALSEXPIRED;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub

        return user.getEnabled() == Constant.USER_ENABLED;
    }

}
