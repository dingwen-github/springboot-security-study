package com.dw.ss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//配置扫描mapper
@MapperScan("com.dw.ss.mapper")
public class SpringbootSecurityStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityStudyApplication.class, args);
    }

}
