package com.john.config;

import feign.Feign;
import feign.Request;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 禁用Feign的熔断功能
 *
 * @author: john
 * @create: 2018-12-18 17:11
 */
public class DisableHystrixFeignConfiguration {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Feign.Builder feignBuilder() {
        return Feign.builder();
//        return HystrixFeign.builder();
   }

   @Bean
   public Request.Options requestOptions() {
        return new Request.Options(1000, 1000);
   }
}
