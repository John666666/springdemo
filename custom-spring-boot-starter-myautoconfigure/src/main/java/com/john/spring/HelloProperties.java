package com.john.spring;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "hello"
)
public class HelloProperties {

    @Setter
    @Getter
    private String name = "张三";

}
