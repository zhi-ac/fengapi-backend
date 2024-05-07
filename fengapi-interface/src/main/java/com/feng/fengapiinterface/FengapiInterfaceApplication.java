package com.feng.fengapiinterface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.feng.fengapiinterface.mapper")
public class FengapiInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FengapiInterfaceApplication.class, args);
    }

}
