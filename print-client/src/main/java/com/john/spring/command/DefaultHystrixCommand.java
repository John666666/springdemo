package com.john.spring.command;

import com.netflix.hystrix.*;

import java.util.concurrent.Callable;

/**
 * 自定义的通用Command
 * @param <E>
 */
public class DefaultHystrixCommand<E> extends HystrixCommand<E> {
        static HystrixCommand.Setter commandSetter;

        private Callable<E> callable;
        private Callable<E> fallBackCallable;

        static {
            HystrixCommandProperties.Setter commandPropertiesSetter = HystrixCommandProperties.Setter()
                    .withExecutionIsolationSemaphoreMaxConcurrentRequests(20)   //最大并发请求数
                    .withExecutionTimeoutInMilliseconds(5000)   //请求超过1.5秒执行降级逻辑
                    .withCircuitBreakerRequestVolumeThreshold(20)    //在一个统计窗口期内，请求失败3次熔断
                    .withCircuitBreakerSleepWindowInMilliseconds(2000)  //开启熔断后多久进入半熔断状态，断路器根据下一次的执行结果决定是否继续开启熔断
                    .withMetricsRollingStatisticalWindowInMilliseconds(10000)   //10秒内如果大于等于3个错误请求就开启熔断
                    .withMetricsRollingPercentileEnabled(false);    //关闭以错误请求百分比为度量指标的逻辑，仅按一个统计时间窗口内错误请求次数计算(主要是方便演示)

            HystrixThreadPoolProperties.Setter threadPoolPropertiesSetter = HystrixThreadPoolProperties.Setter()
                    .withCoreSize(10)
                    .withMaxQueueSize(8)
                    .withQueueSizeRejectionThreshold(4);

            commandSetter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("defalt-hystrix-group"))
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("default-hystrix-threadpool"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("default"))
                    .andCommandPropertiesDefaults(commandPropertiesSetter)
                    .andThreadPoolPropertiesDefaults(threadPoolPropertiesSetter)
            ;
        }
        public DefaultHystrixCommand(Callable callable, Callable fallbackCallable) {
            super(commandSetter);
            this.callable = callable;
            this.fallBackCallable = fallbackCallable;
        }

        @Override
        protected E run() throws Exception {
            return this.callable.call();
        }

        @Override
        protected E getFallback(){
            try {
                return this.fallBackCallable.call();
            } catch (Exception e) {
                throw new RuntimeException("执行降级方法失败", e);
            }
        }
    }