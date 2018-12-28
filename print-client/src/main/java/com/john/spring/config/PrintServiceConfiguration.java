package com.john.spring.config;

import org.springframework.context.annotation.Configuration;

/**
 * @className: PrintServiceConfiguration
 * @description: 通过注解的方式自定义指定service的ribbon配置
 * @author: john
 * @create: 2018-12-14 20:29:19
 */
@Configuration
//@RibbonClient(name = "MICROSERVER-PRINT", configuration = RibbonConfiguration.class) //name对应微服务名称
public class PrintServiceConfiguration {
}
