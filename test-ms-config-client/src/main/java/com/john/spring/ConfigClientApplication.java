package com.john.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Config Client
 *
 * @author: john
 * @create: 2019-01-07 19:50
 */
@EnableDiscoveryClient
@RestController
@RefreshScope
@SpringBootApplication
public class ConfigClientApplication {

    @Value("${tag}")
    private String tag;

    @RequestMapping("/hello")
    public Object hello() {
        return "hello, " + this.tag;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

}
