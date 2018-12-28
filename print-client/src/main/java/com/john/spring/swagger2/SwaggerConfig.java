package com.john.spring.swagger2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token")
                .description("令牌")
                .defaultValue("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                        ".eyJkYXRlX3RpbWUiOjE1MzQzMzIzODE3ODcsInVzZXJfaWQiOiIxMjIxNCIsInVzZXJfbmFtZSI6IuWViuWViuWViuWViiIsInVzZXJfcGhvbmVfbnVtIjoiMTc1MTIwMDA1NTcifQ" +
                        ".tro09ArpPqxxByRtWKomeyJZ2ChK4j7Y9hxBul0QEiU")
                .modelRef(new ModelRef("string"))
                .parameterType("query")
                .required(true)
                .build();
        pars.add(tokenPar.build());
        //添加head参数end


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.john.spring.controller"))// 指定扫描包下面的注解
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                // TODO 安全认证
//                .securityContexts()
//                .securitySchemes()
                ;
    }

    // 创建api的基本信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("集成Swagger2构建RESTful APIs")
                .description("集成Swagger2构建RESTful APIs")
                .termsOfServiceUrl("https://www.baidu.com")
                .contact("zhangsan")
                .version("1.0.0")
                .build();
    }
}