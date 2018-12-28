package com.john.spring.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class PrintController {

    private Logger logger = LoggerFactory.getLogger(PrintController.class);

    @Value("${server.port}")
    private int port;

    @Autowired
    private Registration registration;

    @RequestMapping("/print/{msg}")
    public String simplePrint(@PathVariable("msg") String msg) {
        return registration.getServiceId()+"-"+registration.getHost() + ":" +registration.getPort()+", print msg: " + msg;
    }

}
