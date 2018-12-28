package com.john.spring.service;

import com.john.config.DisableHystrixFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="MICROSERVER-PRINT", fallback = PrintServiceFallback.class, configuration = DisableHystrixFeignConfiguration.class)
public interface PrintService {
    @GetMapping("/rest/print/{msg}")
    String print(@PathVariable("msg") String msg);
}

@Component
class PrintServiceFallback implements PrintService {
    @Override
    public String print(String msg) {
        return "打印机忙，无法打印";
    }
}
