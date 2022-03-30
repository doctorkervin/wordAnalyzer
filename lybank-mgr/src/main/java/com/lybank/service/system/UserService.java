package com.lybank.service.system;

import com.lybank.config.interceptor.UserPrincipal;
import com.lybank.entity.system.QUser;
import com.lybank.entity.system.User;
import com.lybank.repository.system.UserRep;
import com.lybank.service.BaseService;
import com.lybank.util.MyBeanUtils;
import com.lybank.util.Result;
import com.lybank.util.SpringCacheKeyGenerator;
import com.lybank.util.code.UserModel;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 业务层
 *
 * @author xiaoqi 2018年10月3日
 */
@Service
public class UserService extends BaseService {
    @Autowired
    private UserRep userRep;


    /**
     * 获取当前登录的用户
     *
     * @return
     */
//    @Cacheable(value="user",keyGenerator=SpringCacheKeyGenerator.KEYGENERATOR)
    public UserPrincipal getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) return null;
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    @Cacheable(value = "user", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public User getByOne(Long id) {
        return userRep.getOne(id);
    }


    /**
     * 查找用户
     *
     * @param id
     * @return
     */
    @Cacheable(value = "user", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public User findById(Long id) {
        return userRep.findById(id).orElse(null);
    }

    /**
     * 根据用户名查找用户
     *
     * @param loginName
     * @return
     */
    @Cacheable(value = "user", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public User loadUserByUsername(String loginName) {
        // TODO Auto-generated method stub
        User u = userRep.findOneByLoginName(loginName);
        return u;
    }

    /**
     * 更新用户密码
     *
     * @param id
     * @param newpassword
     * @param oldpassword
     * @return
     */
    @Transactional
    @CacheEvict(value = "user", allEntries = true)
    public Result updatePwd(Long id, String newpassword, String oldpassword) {
        User u = userRep.getOne(id);
        BCryptPasswordEncoder bcencoder = new BCryptPasswordEncoder(11);
        //判断原密码是否正确
        if (!bcencoder.matches(oldpassword, u.getPwd())) return new Result().ResultFail().setMsg("原密码不正确，请重新输入！");
        if (newpassword.equals(oldpassword)) {
            return new Result().ResultFail().setMsg("新旧密码不能相同!");
        }
        String ens = bcencoder.encode(newpassword);
        u.setPwd(ens);
        u.setOperaterId(getUserId());
        u.setOperaterTime(new Date());
        userRep.saveAndFlush(u);
        return new Result().ResultSuccess().setMsg("密码更新成功！下次登录后生效");
    }

    public boolean isPwdCorrect(User u, String inpassword, BCryptPasswordEncoder bcencoder) {
        //BCryptPasswordEncoder bcencoder = new BCryptPasswordEncoder(11);
        String encStr = bcencoder.encode(inpassword);
        return encStr.equals(u.getPwd());

    }

    @CacheEvict(value = "user", allEntries = true)
    public User addUser(User u) {
        BCryptPasswordEncoder bcencoder = new BCryptPasswordEncoder(11);
        Optional<User> optional = Optional.of(u);
        if (optional.isPresent()) {
            if (null != u.getPwd() && !"".equals(u.getPwd())) {
                u.setPwd(bcencoder.encode(u.getPwd()));
            }
        }
        Date date = new Date();
        if (u.getCreateTime() == null)
            u.setCreateTime(date);
        Long userId = getUserId();
        u.setCreateId(userId);
        u.setOperaterId(userId);
        u.setOperaterTime(date);
        return userRep.save(u);
    }

    @Cacheable(value = "user", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public Page<User> listUsers(Pageable page) {
        Page<User> pu = userRep.findAll(page);
        return pu;
    }

    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "user", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public void delUser(List<Long> idsList) {
        List<User> users = userRep.findAllById(idsList);
        userRep.deleteAll(users);
    }

    @Cacheable(value = "user", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public List<User> findByLoginName(String loginName) {
        // TODO Auto-generated method stub
        return userRep.findByLoginName(loginName);
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @Transactional
    @CacheEvict(value = "user", allEntries = true)
    public User updateUser(User user) {
        // TODO Auto-generated method stub
        BCryptPasswordEncoder bcencoder = new BCryptPasswordEncoder(11);
        User temp = userRep.findById(user.getId()).get();
        if (!StringUtils.isEmpty(user.getPwd()) && !user.getPwd().equals(temp.getPwd())) {
            user.setPwd(bcencoder.encode(user.getPwd()));
        }
        MyBeanUtils.copyPropertiesIgnoreNull(user, temp);
        temp.setOperaterId(getUserId());
        temp.setOperaterTime(new Date());
        return userRep.saveAndFlush(temp);

    }

    /**
     * 搜索用户
     *
     * @param user
     * @param pageable
     * @return
     */
//    @Cacheable(value = "user", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public Page<User> searchUser(UserModel user, Pageable pageable) {
        // TODO Auto-generated method stub
        QUser qu = QUser.user;
        BooleanBuilder builder = new BooleanBuilder();
        //过滤超级管理员账户
        builder.and(qu.loginName.notIn("admin"));
        if (user.getStartTime() != null) {
            builder.and(qu.createTime.goe(user.getStartTime()));
        }
        if (user.getEndTime() != null) {
            builder.and(qu.createTime.loe(user.getEndTime()));
        }
        if (user.getLoginName() != null) {
            builder.and(qu.loginName.like("%" + user.getLoginName() + "%"));
        }

        return userRep.findAll(builder, pageable);
    }

    /**
     * 查询所有启用用户
     *
     * @return
     */
    @Cacheable(value = "user", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public List<User> findByUserList(int enabled) {
        BooleanBuilder b = new BooleanBuilder();
        QUser q = QUser.user;
        b.and(q.enabled.eq(enabled));
        b.and(q.loginName.notIn("admin"));
        return (List<User>) userRep.findAll(b);
    }
}
