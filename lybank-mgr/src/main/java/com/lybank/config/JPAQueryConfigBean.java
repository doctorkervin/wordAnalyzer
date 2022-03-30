package com.lybank.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.persistence.EntityManager;

@Configuration
public class JPAQueryConfigBean {


    /**
     * 实体dsl 查询工厂
     *
     * @param entityManager
     * @return
     */
    @Bean
    @Scope("prototype")
    //@Autowired
    // @PersistenceContext
    public JPAQueryFactory jpaQuery(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
