package com.john.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟一个从配置中心拉取配置的服务
 *
 * @author: john
 * @create: 2018-12-28 12:47
 */
@SpringBootApplication
@RestController
@RefreshScope
public class ConfigClientApplication {

    @Value("${tag}")
    private String tag;

    @Value("${user.home}")
    private String userHome;

    @RequestMapping("/hello")
    public Object hello() {
        return "hello: " + this.tag +"\n<br /> user-home: " + this.userHome;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
