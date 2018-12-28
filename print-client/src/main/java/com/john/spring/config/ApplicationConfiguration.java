package com.john.spring.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * 用用全局配置
 *
 * @author: john
 * @create: 2018-12-14 20:37:14
 */
@Configuration
//@RibbonClients(defaultConfiguration = RibbonConfiguration.class)    //配置ribbon全局策略
public class ApplicationConfiguration {
    @Bean
    @LoadBalanced
    public RestTemplate setRestTemplate() {
        return new RestTemplate();
    }
}


