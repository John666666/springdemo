package com.john.config;

import com.netflix.hystrix.*;
import feign.Request;
import feign.hystrix.HystrixFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 打印服务Feign配置
 *
 * @author: john
 * @create: 2018-12-18 11:14
 */
@Configuration
public class DefaultFeignWithHystrixConfiguration {
    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(1500, 1500);
    }

    @Bean
    public HystrixFeign.Builder feignBuilder() {
        return HystrixFeign.builder().setterFactory((target, method) -> {
            HystrixCommandProperties.Setter commandPropertiesSetter = HystrixCommandProperties.Setter()
//                    .withExecutionIsolationSemaphoreMaxConcurrentRequests(20)   //最大并发请求数
                    .withExecutionTimeoutInMilliseconds(7000)   //请求超过1.5秒执行降级逻辑
                    .withCircuitBreakerRequestVolumeThreshold(20)    //在一个统计窗口期内，请求失败3次熔断
                    .withCircuitBreakerSleepWindowInMilliseconds(2000)  //开启熔断后多久进入半熔断状态，断路器根据下一次的执行结果决定是否继续开启熔断
                    .withMetricsRollingStatisticalWindowInMilliseconds(10000)   //10秒内如果大于等于3个错误请求就开启熔断
                    .withMetricsRollingPercentileEnabled(false);    //关闭以错误请求百分比为度量指标的逻辑，仅按一个统计时间窗口内错误请求次数计算(主要是方便演示)

            HystrixThreadPoolProperties.Setter threadPoolPropertiesSetter = HystrixThreadPoolProperties.Setter()
                    .withCoreSize(10)
                    .withMaxQueueSize(8)
                    .withQueueSizeRejectionThreshold(4);

//            String groupKey = target.name();
//            String commandKey = Feign.configKey(target.type(), method);
            String groupKey = "default";
            String commandKey = "default";
            return HystrixCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                    .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                    .andCommandPropertiesDefaults(commandPropertiesSetter)
                    .andThreadPoolPropertiesDefaults(threadPoolPropertiesSetter);
        });
    }

}
