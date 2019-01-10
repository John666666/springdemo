package com.john.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Config Server
 *
 * @author: john
 * @create: 2019-01-07 11:23
 */
@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class MsConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsConfigServerApplication.class, args);
    }
}
