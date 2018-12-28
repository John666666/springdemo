package com.john.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {

    @Autowired
    private HelloService helloService;

    @Test
    public void testCustomStarter() {
        helloService.sayHello();
    }

}
