package com.zhouzhaoping.springdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhouzhaoping.springdemo.service.LambdaService;

@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private LambdaService lambdaService;

    @RequestMapping("/")
    public String index() {
        lambdaService.Test();
        return "hello";
    }
}
