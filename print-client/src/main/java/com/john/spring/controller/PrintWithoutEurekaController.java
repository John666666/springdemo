package com.john.spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 测试脱离eureka单独使用ribbon
 * 注意：本测试必须设置ribbon.eureka.enabled=false，because eureka will override ribbonServerList to DiscoveryEnabledNIWSServerList
 * @author: john
 * @create: 2018-12-14 21:25:01
 */
@Api("test ribbon without eureka")
@Controller
@RequestMapping("/print2")
public class PrintWithoutEurekaController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Value("${custom.params.printServerWithoutEurekaUrl}")
    private String printServerUrl;

    @ApiOperation(value = "ribbon负载均衡单独使用", notes = "说明：用于演示ribbon负载均衡单独使用")
    @GetMapping("/html/{msg}")
    public String htmlPrinter(@ApiParam @PathVariable String msg, @ApiIgnore ModelMap map) {

        // 直接通过LoadBalancerClient使用ribbon api
        /*ServiceInstance serviceInstance = loadBalancer.choose("print");
        String url = String.format(printServerUrl, serviceInstance.getHost() + ":" + serviceInstance.getPort());
        map.addAttribute("printResult", "ribbon选择的访问地址：" + url);*/

        // 通过 restTemplate使用ribbon loadbalancer
        String result = restTemplate.getForObject(printServerUrl+msg, String.class);
        map.addAttribute("printResult", result);
        return "result";
    }
}
