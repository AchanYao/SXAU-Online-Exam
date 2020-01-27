package com.achan.exam.commons;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@SpringBootConfiguration
@ComponentScan("com.achan.exam.common")
@EnableAutoConfiguration
@MapperScan("com.achan.exam.common.mapper")
class CommonsApplicationTests {

    @Test
    void contextLoads() {
    }

}
