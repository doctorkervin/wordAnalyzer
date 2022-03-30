package com.lybank.service;

import com.lybank.config.interceptor.UserPrincipal;
import com.lybank.entity.system.User;
import com.lybank.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 阿布
 * @ClassName com.lybank.service.BaseService
 * @Description 公共Service
 * @Date 2019-01-17
 * @Version 1.0
 */
public class BaseService {
    @Autowired
    protected UserService userService;

    /**
     * 处理ID字符串，逗号分隔
     *
     * @param idStr
     * @return
     */
    protected List<Long> getIdLongList(String idStr) {
        String[] strList = idStr.split(",");
        List<Long> idList = new ArrayList<Long>();
        for (String str : strList) {
            idList.add(Long.parseLong(str));
        }
        return idList;
    }

    /**
     * @return UserPrincipal
     * @Titel 获取登陆用户信息
     * @Description 获取登陆用户信息
     * @Author ABu
     * @DateTime 2019/7/9 13:52
     */
    private UserPrincipal getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) return null;
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * @return com.lybank.entity.system.User 返回用户书体类
     * @Titel 获取用户登陆实体类
     * @Description 获取用户登陆实体类
     * @Author ABu
     * @DateTime 2019/7/9 13:54
     */
    protected User getUser() {
        UserPrincipal up = getCurrentUser();
        return up != null ? up.getUser() : null;
    }

    /**
     * @return java.lang.Long 返回用户ID
     * @Titel 获取用户登陆ID
     * @Description 获取用户登陆ID
     * @Author ABu
     * @DateTime 2019/7/9 13:55
     */
    protected Long getUserId() {
        User u = getUser();
        return u != null ? u.getId() : null;
    }


}
