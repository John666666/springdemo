package com.john.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
@EnableDiscoveryClient
public class ConsulConfigClient {
    public static void main(String[] args) {
        SpringApplication.run(ConsulConfigClient.class, args);
    }

    @Value("${name}")
    private String name;

    @GetMapping("/hello")
    public Object hello() {
        return "hello, " + this.name;
    }

}
