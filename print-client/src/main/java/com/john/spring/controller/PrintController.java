package com.john.spring.controller;

import com.john.spring.service.PrintService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

@Api
@Controller
@RequestMapping("/print")
public class PrintController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PrintService printService;

    @Value("${custom.params.printServerUrl}")
    private String printServerUrl;

    @ApiOperation(value = "HTML打印服务", notes = "说明：用于通过HTML打印文字")
    @GetMapping("/html/{msg}")
    public String htmlPrinter(@ApiParam @PathVariable String msg, @ApiIgnore ModelMap map) {
//        String result = restTemplate.getForObject(printServerUrl+msg, String.class);
        String result = printService.print(msg);
        map.addAttribute("printResult", result);
        return "result";
    }

    @ApiOperation(value = "简单打印服务", notes = "说明：用于控制台打印文字")
    @GetMapping("/text/{msg}")
    @HystrixCommand
    @ResponseBody
    public String textPrinter(@ApiParam @PathVariable String msg) {
        String result = restTemplate.getForObject(printServerUrl+msg, String.class);
//        String result = printService.print(msg);
        return "printResult: " + result;
    }

}
