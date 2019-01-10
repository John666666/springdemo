package com.john.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

/**
 * Zipkin Server
 *
 * @author: john
 * @create: 2019-01-09 21:53
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipKinApplication {
    public static void main(String[] args) {
//        ImageBanner banner = new ImageBanner(new ClassPathResource("banner.png"));
//        new SpringApplicationBuilder(ZipKinApplication.class).banner(banner).build().run(args);
        SpringApplication.run(ZipKinApplication.class, args);
    }
}
