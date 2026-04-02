package com.liang.test;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@MapperScan("com.liang.test.mapper")
class TestApplicationTests {

    @Test
    void contextLoads() {
    }

}
