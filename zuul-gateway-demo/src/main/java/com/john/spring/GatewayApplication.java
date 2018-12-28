package com.john.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * Gateway
 *
 * @author: john
 * @create: 2018-12-20 13:17
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayApplication {

    // 从符合servicePattern的服务名中抽取出符合条件的内容，注入到路由模式中。
    // 下面的配置可以将服务"myusers-v1"映射成"/myusers/v1/**"
    // 注：只针对注册到服务中心的服务生效
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)",
                "${name}/${version}");
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
