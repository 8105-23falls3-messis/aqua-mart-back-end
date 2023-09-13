package com.aqua.fall23g1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aqua.fall23g1.mapper")
public class Fall23g1Application {

    public static void main(String[] args) {
        SpringApplication.run(Fall23g1Application.class, args);
    }

}
