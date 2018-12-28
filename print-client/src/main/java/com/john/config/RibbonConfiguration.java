package com.john.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过注解的方式自定义ribbon策略
 * 可自定义的属性参见：https://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/2.0.2.RELEASE/single/spring-cloud-netflix.html#_customizing_the_ribbon_client
 * 注意：此配置不可放置到ComponentScan可以扫描到的位置（一般来说可以放到Application启动类的上层包中），否则就会全局生效
 */
@Configuration  //官方文档说配置类必须加上此注解，但本人测试不加也可以，建议最好还是按官方要求加上
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule() {
        return new RoundRobinRule();
    }
}
