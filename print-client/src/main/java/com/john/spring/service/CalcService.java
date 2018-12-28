package com.john.spring.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * 计算服务调用接口
 *
 * @author: john
 * @create: 2018-12-14 22:18
 */
@FeignClient(value = "MICROSERVER-CALC", fallback = CalcServiceFallback.class)
public interface CalcService {

    @GetMapping(value = "/calc/add/{num1}_{num2}")
    Map<String, Object> add(@PathVariable("num1") int num1, @PathVariable("num2") int num2);

    @GetMapping(value = "/calc/minus/{num1}_{num2}")
    Map<String, Object> minus(@PathVariable("num1") int num1, @PathVariable("num2") int num2);
}

@Component
class CalcServiceFallback implements CalcService {

    @Override
    public Map<String, Object> add(int num1, int num2) {
        Map<String, Object> result = new HashMap<>();
        result.put("result", num1 + num2);
        result.put("message", "计算微服务不可用，服务降级：返回本地计算结果");
        result.put("code", "500");
        return result;
    }

    @Override
    public Map<String, Object> minus(int num1, int num2) {
        Map<String, Object> result = new HashMap<>();
        result.put("result", num1 - num2);
        result.put("message", "计算微服务不可用，服务降级：返回本地计算结果");
        result.put("code", "500");
        return result;
    }
}
