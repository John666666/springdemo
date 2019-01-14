package com.john.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class CalcApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalcApplication.class, args);
    }
}
