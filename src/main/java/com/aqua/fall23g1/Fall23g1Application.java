package com.aqua.fall23g1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aqua.fall23g1.service.ImageStorageService;

import jakarta.annotation.Resource;

@SpringBootApplication
@MapperScan("com.aqua.fall23g1.mapper")
public class Fall23g1Application  implements CommandLineRunner {
	
	 @Resource
	ImageStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(com.aqua.fall23g1.Fall23g1Application.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
      //storageService.deleteAll();
      storageService.init();
    }
}
