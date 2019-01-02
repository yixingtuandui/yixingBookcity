package com.tecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan("com.tecode.dao")
public class WuxingbookcityApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuxingbookcityApplication.class, args);
    }

}

