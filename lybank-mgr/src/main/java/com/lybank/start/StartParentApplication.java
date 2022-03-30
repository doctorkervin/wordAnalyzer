package com.lybank.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.lybank")
@EntityScan("com.lybank.entity")
@EnableJpaRepositories(basePackages = "com.lybank.repository")
@EnableCaching
public class StartParentApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartParentApplication.class, args);
    }
}
