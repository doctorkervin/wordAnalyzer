package com.lybank.repository.system;


import com.lybank.entity.system.User;
import com.lybank.repository.BaseRep;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface UserRep extends BaseRep<User, Long>, QuerydslPredicateExecutor<User> {

    User findOneByLoginName(String loginName);

    List<User> findByLoginName(String loginName);

    //Page<User> findAll(Pageable page);

}
