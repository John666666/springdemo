package com.john.config;

import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 打印服务Feign配置
 *
 * @author: john
 * @create: 2018-12-18 11:14
 */
@Configuration
public class PrintFeignConfiguration {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }
    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(1500, 1500);
    }
}
