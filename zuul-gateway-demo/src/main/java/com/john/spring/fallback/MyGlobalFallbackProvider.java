package com.john.spring.fallback;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 服务级降级
 *
 * @author: john
 * @create: 2018-12-25 18:37
 */
@Component
@Slf4j
public class MyGlobalFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        return null;    //返回要设置降级的微服务名称或者配置的route名称，return "*"或者null 匹配所有微服务和路由（全局降级配置）
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return response(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        log.error("访问出错", cause);
        if(cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return fallbackResponse();
        }
    }

    public ClientHttpResponse response(final HttpStatus httpStatus) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return httpStatus;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return httpStatus.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return httpStatus.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("服务不可用，请稍后再试。".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                // headers设定
                HttpHeaders headers = new HttpHeaders();
//                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                MediaType mt = MediaType.APPLICATION_JSON_UTF8;
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}
