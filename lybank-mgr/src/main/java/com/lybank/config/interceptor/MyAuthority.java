package com.lybank.config.interceptor;

import com.lybank.entity.system.Authority;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限接口实现类
 */
public class MyAuthority extends Authority implements GrantedAuthority {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.getCode();
    }

}
