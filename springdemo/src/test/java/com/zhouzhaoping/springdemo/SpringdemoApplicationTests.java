package com.zhouzhaoping.springdemo;

import com.zhouzhaoping.springdemo.service.LambdaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringdemoApplicationTests {

    @Autowired
    private LambdaService lambdaService;
    @Test
    void test() {
        lambdaService.Chapter2();
        lambdaService.Chapter3();
        lambdaService.Test();
    }

}
