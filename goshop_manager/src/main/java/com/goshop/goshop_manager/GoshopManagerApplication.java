package com.goshop.goshop_manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.goshop.mapper")
public class GoshopManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoshopManagerApplication.class, args);
    }

}

