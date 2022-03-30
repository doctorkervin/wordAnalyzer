package com.lybank.repository.system;

import com.lybank.entity.system.Authority;
import com.lybank.repository.BaseRep;

import java.util.List;

public interface AuthorityRep extends BaseRep<Authority, Long> {

    List<Authority> findAllByType(String type);

    List<Authority> findAllByParentId(Long pid);

    List<Authority> findAllByParentIdIn(List<Long> idsList);

    List<Authority> findAllByTypeAndParentId(String type, Long pid);

    List<Authority> findAllByTypeAndParentIdIsNotNull(String type);

    List<Authority> findAllByTypeAndCode(String type, String code);

    List<Authority> findAllByCode(String code);

}
