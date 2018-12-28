package com.john.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({HelloService.class })
@EnableConfigurationProperties({HelloProperties.class})
public class HelloAutoConfiguration {

    @Autowired
    private HelloProperties properties;

    @Bean
    @ConditionalOnMissingBean(HelloService.class)
    public HelloService createHelloService() {
        HelloService service = new HelloService();
        service.setName(this.properties.getName());
        return service;
    }

}
