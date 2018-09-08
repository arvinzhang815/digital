package com.yingwu.digital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yingwu.digital")
public class DigitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalApplication.class, args);
    }
}
