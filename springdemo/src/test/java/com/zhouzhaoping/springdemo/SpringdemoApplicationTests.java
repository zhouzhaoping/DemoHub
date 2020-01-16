package com.zhouzhaoping.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zhouzhaoping.springdemo.service.LambdaService;

@SpringBootTest
class SpringdemoApplicationTests {

    @Autowired
    private LambdaService lambdaService;
    @Test
    void test() {
        //lambdaService.Chapter2();
        //lambdaService.Chapter3();
        //lambdaService.Test();
        lambdaService.Chapter4();
    }

}
