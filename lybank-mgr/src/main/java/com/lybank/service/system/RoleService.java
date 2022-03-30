package com.lybank.service.system;

import com.lybank.entity.system.Authority;
import com.lybank.entity.system.QRole;
import com.lybank.entity.system.Role;
import com.lybank.repository.system.RoleRep;
import com.lybank.util.MyBeanUtils;
import com.lybank.util.SpringCacheKeyGenerator;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRep roleRep;

    @CacheEvict(value = "role", allEntries = true)
    public Role addRole(Role role) {
        return roleRep.save(role);
    }

    @CacheEvict(value = "role", allEntries = true)
    public void deleteRole(Role entity) {
        entity.setAuthorities(null);
        roleRep.delete(entity);
    }

    /**
     * 查找所有角色 分页
     *
     * @return
     */
    @Cacheable(value = "role", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public Page<Role> listRole(Role p, Pageable page) {
        QRole qp = QRole.role;
        BooleanBuilder b = new BooleanBuilder();
        if (!StringUtils.isEmpty(p.getName())) {
            b.and(qp.name.like("%" + p.getName() + "%"));
        }
        if (!StringUtils.isEmpty(p.getCode())) {
            b.and(qp.code.eq(p.getCode()));
        }
        return roleRep.findAll(b, page);

    }

    /**
     * 查找角色权限
     *
     * @param r
     * @param auth
     * @return
     */
    @Cacheable(value = "role", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public Iterable<Role> getPermissionAuthority(Role r, Authority auth) {
        QRole qr = QRole.role;
        BooleanBuilder b = new BooleanBuilder();
        if (!StringUtils.isEmpty(r.getId())) {
            b.and(qr.eq(r));
        }
        if (!StringUtils.isEmpty(auth.getId())) {
            b.and(qr.authorities.contains(auth));
        }
        return roleRep.findAll(b);
    }

    /**
     * 查找角色
     *
     * @return
     */
    @Cacheable(value = "role", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public Role getRoleById(Long id) {
        return roleRep.findById(id).get();
    }

    @Transactional
    @CacheEvict(value = "role", allEntries = true)
    public Role addOrUpdateRole(Role p) {
        if (p != null && p.getId() != null) {
            // 更新
            Role temp = roleRep.findById(p.getId()).get();
            MyBeanUtils.copyPropertiesIgnoreNull(p, temp);
            return roleRep.saveAndFlush(temp);
        } else {
            // 添加
            return roleRep.save(p);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "role", allEntries = true)
    public List<Role> delAll(List<Long> idsList) {
        // TODO Auto-generated method stub
        List<Role> pes = roleRep.findAllById(idsList);
        for (Role r : pes) {//去除关联关系
            r.setAuthorities(null);
        }
        roleRep.deleteAll(pes);
        return pes;
    }

    /**
     * 更新用户权限
     *
     * @param r
     * @param auths
     * @return
     */
    @CacheEvict(value = "role", allEntries = true)
    public Role addOrUpdateRoleWithAuthorities(Role r, List<Authority> auths) {

        if (r != null && r.getId() != null) {
            Role temp = roleRep.findById(r.getId()).get();
            MyBeanUtils.copyPropertiesIgnoreNull(r, temp);
            temp.setAuthorities(null);
            roleRep.save(temp);
            temp.setAuthorities(auths);
            return roleRep.saveAndFlush(temp);

        } else {

            roleRep.saveAndFlush(r);
            r.setAuthorities(auths);
            roleRep.save(r);
        }

        return r;
    }

    /**
     * 查找所有角色
     *
     * @return
     */
    @Cacheable(value = "role", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public List<Role> findAll() {
        // TODO Auto-generated method stub
        return roleRep.findAll();
    }

}
