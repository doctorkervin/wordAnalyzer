package com.lybank.service.system;

import com.lybank.entity.system.Authority;
import com.lybank.entity.system.QAuthority;
import com.lybank.repository.system.AuthorityRep;
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
public class AuthorityService {

    @Autowired
    private AuthorityRep authorityRep;

    @CacheEvict(value = "authority", allEntries = true)
    public Authority addAuthority(Authority authority) {
        return authorityRep.save(authority);
    }

    @CacheEvict(value = "authority", allEntries = true)
    public void deleteAuthority(Authority entity) {
        authorityRep.delete(entity);
    }

    @Cacheable(value = "authority", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public Page<Authority> listAuthority(Authority p, Pageable page) {
        QAuthority qp = QAuthority.authority;
        BooleanBuilder b = new BooleanBuilder();
        if (!StringUtils.isEmpty(p.getName())) {
            b.and(qp.name.like("%" + p.getName() + "%")).or(qp.code.like("%" + p.getName() + "%"));
        }
        if (!StringUtils.isEmpty(p.getCode())) {
            b.and(qp.code.eq(p.getCode()));
        }
        return authorityRep.findAll(b, page);
    }

    @Cacheable(value = "authority", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public List<Authority> getAuthorityListByType(String type) {

        return authorityRep.findAllByType(type);
    }

    @Cacheable(value = "authority", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public List<Authority> getAuthorityListByTypeAndCode(String type, String code) {

        return authorityRep.findAllByTypeAndCode(type, code);
    }

    @Cacheable(value = "authority", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public List<Authority> getAuthorityListByCode(String code) {

        return authorityRep.findAllByCode(code);
    }

    @Cacheable(value = "authority", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public List<Authority> getAuthorityListByParentId(Long pid) {

        return authorityRep.findAllByParentId(pid);
    }

    @Cacheable(value = "authority", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public Authority getAuthorityById(Long id) {
        // TODO Auto-generated method stub

        return authorityRep.findById(id).get();
    }

    @Transactional
    @CacheEvict(value = "authority", allEntries = true)
    public Authority addOrUpdateAuthority(Authority p) {
        if (p != null && p.getId() != null) {
            //更新
            Authority temp = authorityRep.findById(p.getId()).get();
            MyBeanUtils.copyPropertiesIgnoreNull(p, temp);
            if ((p.getParent() == null) || p.getParent().getId() == 0) temp.setParent(null);
            return authorityRep.saveAndFlush(temp);
        } else {
            //添加
            if ((p.getParent() == null) || p.getParent().getId() == 0) p.setParent(null);
            return authorityRep.save(p);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "authority", allEntries = true)
    public List<Authority> delAll(List<Long> idsList) {
        // TODO Auto-generated method stub
        List<Authority> pes = authorityRep.findAllById(idsList);
        authorityRep.deleteAll(pes);
        return pes;
    }

    @Cacheable(value = "authority", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public List<Authority> getAuthorityListByParentIdIn(List<Long> idsList) {
        return authorityRep.findAllByParentIdIn(idsList);
    }

    @Cacheable(value = "authority", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public List<Authority> getAuthoritysListByTypeAndParentId(String type, Long pid) {
        // TODO Auto-generated method stub
        return authorityRep.findAllByTypeAndParentId(type, pid);
    }

    @Cacheable(value = "authority", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public List<Authority> getAuthoritysListByTypeAndParentIdIsNotNull(String type) {
        // TODO Auto-generated method stub
        return authorityRep.findAllByTypeAndParentIdIsNotNull(type);
    }

    @Cacheable(value = "authority", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public List<Authority> findAllById(List<Long> auLongs) {
        // TODO Auto-generated method stub
        return authorityRep.findAllById(auLongs);
    }
}
