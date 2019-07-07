package com.orange.minip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.orange.minip.Mapper")
@SpringBootApplication
public class MinipApplication {
    public static void main(String[] args) {
        SpringApplication.run(MinipApplication.class, args);
    }

}
