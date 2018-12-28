package com.john.spring;

import lombok.Getter;
import lombok.Setter;

public class HelloService {

    @Getter
    @Setter
    private String name;

    public void sayHello() {
        System.out.println("hello, " + this.name);
    }
}
