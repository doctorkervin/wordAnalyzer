package com.lybank.config.interceptor;

import com.lybank.entity.system.Authority;
import com.lybank.entity.system.Role;
import com.lybank.entity.system.User;
import com.lybank.service.system.AuthorityService;
import com.lybank.service.system.RoleService;
import com.lybank.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 权限拦截类
 */
@Component
public class MyPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        // TODO Auto-generated method stub
		/*String username=authentication.getName();
		User user=userService.loadUserByUsername(username);*/
        if ("role".equals(targetDomainObject)) {

            return this.hasRole(authentication, permission);

        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        //targerType menus button
        // TODO Auto-generated method stub

        return this.hasPermission(authentication, targetType, permission);
    }

    /**
     * 简单的字符串比较，相同则认为有权限
     */

    private boolean hasRole(Authentication authentication, Object permission) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {

            if (authority.getAuthority().equals(permission)) {

                return true;
            }
        }

        return false;

    }

    private boolean hasPermission(Authentication authentication, String targetType, Object permission) {

        List<Authority> auths = authorityService.getAuthorityListByTypeAndCode(targetType, permission.toString());
        String username = authentication.getName();
        User user = userService.loadUserByUsername(username);
        if (auths.size() <= 0) return false;//权限不存在
        if (user == null) return false;//用户不存在
        if (user.getRole() == null) return false;//角色不存在
        List<Role> rs = (List<Role>) roleService.getPermissionAuthority(user.getRole(), auths.get(0));

        if (rs.size() > 0) return true;
		     /*List<Authority> authorities=user.getRole().getAuthorities();
		      for (Authority authority : authorities) {
		         if (authority.getType().equals(targetType)&&authority.getCode().equals(permission)) {
		            return true;

		         }

		      }
*/
        return false;

    }

}
