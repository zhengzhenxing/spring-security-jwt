package com.example.springsecurityjwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.springsecurityjwt.dao")
@SpringBootApplication
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

}
