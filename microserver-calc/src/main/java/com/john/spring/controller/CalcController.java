package com.john.spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/calc")
public class CalcController {

    @Autowired
    private Registration registration;

    @Value("${custom.params.sleep-time}")
    private int sleepTime;      // 通过${random.int(10)}这种方式配置的随机数只是启动的时候生成一次，运行过程中不会变化

    @GetMapping("/add/{num1}_{num2}")
    public Map<String, Object> add(@PathVariable int num1, @PathVariable int num2) throws Exception {

        /*if(true) {
            throw new Exception("人为异常");
        }*/

        this.sleepTime = new Random().nextInt(2100);
        log.info("sleepTime: " + sleepTime);
        Thread.sleep(sleepTime);

        Map<String, Object> result = new HashMap<>();
        result.put("host", registration.getHost());
        result.put("port", registration.getPort());
        result.put("result", num1 + num2);
        return result;
    }

    @GetMapping("/minus/{num1}_{num2}")
    public Map<String, Object> minus(@PathVariable int num1, @PathVariable int num2) {
        Map<String, Object> result = new HashMap<>();
        result.put("host", registration.getHost());
        result.put("port", registration.getPort());
        result.put("result", num1 - num2);
        return result;
    }

}
