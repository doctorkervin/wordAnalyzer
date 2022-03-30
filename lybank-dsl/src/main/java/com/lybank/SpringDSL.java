package com.lybank;

import javax.persistence.EntityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.querydsl.jpa.impl.JPAQueryFactory;

/*@SpringBootApplication*/
@EnableJpaRepositories(basePackages = {"com.lybank.repository.pay","com.lybank.repository.system"})
@EntityScan(basePackages = {"com.lybank.entity.pay","com.lybank.entity.system"})
public class SpringDSL {

    public static void main(String[] args) {
        SpringApplication.run(SpringDSL.class, args);
    }
    @Bean
    //@Autowired
    public JPAQueryFactory jpaQuery(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }

}
