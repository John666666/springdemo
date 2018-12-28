package com.john.spring.controller;

import com.john.spring.service.CalcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Api
@RestController
@RequestMapping("/calc")
@Slf4j
public class CalcController {

    @Autowired
    private RestTemplate restTemplate;

    //    @Resource(type = CalcService.class)
    @Autowired
    private CalcService calcService;

    @Value("${custom.params.calcServerUrl}")
    private String calcServerUrl;

    @ApiOperation(value = "加法计算")
    /*@HystrixCommand(
            fallbackMethod = "addFallback", commandKey = "calc-add-command",
            groupKey = "calc-command-group", threadPoolKey = "calc-add-command-pool",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "30"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "metrics.rollingPercentile.enabled", value = "false")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "10"),
                    @HystrixProperty(name = "maxQueueSize", value = "18"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "4")
            }
    )*/
    @GetMapping("/add/{num1}_{num2}")
    public Map<String, Object> add(@ApiParam @PathVariable int num1, @ApiParam @PathVariable int num2) {
        log.info("request calc.add, params: " + num1 + "," + num2);
//        Map<String, Object> result = restTemplate.getForObject(calcServerUrl + "add/" + num1 + "_" + num2, Map.class);
        Map<String, Object> result = calcService.add(num1, num2);   // request by feign client
        return result;
    }

    /*public Map<String, Object> addFallback(int num1, int num2) {
        Map<String, Object> result = new HashMap<>();
        result.put("result", num1 + num2);
        result.put("message", "计算微服务不可用，服务降级：返回本地计算结果");
        result.put("code", "500");
        return result;
    }*/

    @ApiOperation(value = "减法计算")
    @GetMapping("/minus/{num1}_{num2}")
    public Map<String, Object> minus(@ApiParam @PathVariable int num1, @ApiParam @PathVariable int num2) {
//        Map<String, Object> result = restTemplate.getForObject(calcServerUrl + "minus/" + num1 + "_" + num2, Map.class);
        Map<String, Object> result = calcService.minus(num1, num2);
        return result;
        /*return new DefaultHystrixCommand<Map<String, Object>>(
                () -> restTemplate.getForObject(calcServerUrl + "minus/" + num1 + "_" + num2, Map.class),
                () -> minusFallback(num1, num2)
        ).execute();*/
    }

    /*public Map<String, Object> minusFallback(int num1, int num2) {
        Map<String, Object> result = new HashMap<>();
        result.put("result", num1 - num2);
        result.put("message", "计算微服务不可用，服务降级：返回本地计算结果");
        result.put("code", "500");
        return result;
    }*/

}
