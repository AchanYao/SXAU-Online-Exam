package com.achan.exam.commons.service;

import com.achan.exam.common.service.impl.StudentServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Achan
 * @date 2020/1/20
 */
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentServiceImpl studentService;

    @Test
    public void testGetDetails() {
        studentService.getStudentWithDetails(new Page<>(1, 10)).getRecords().forEach(System.out::println);
    }

    @Test
    public void testGetDetailsWithKeyword() {
        studentService.getStudentWithDetailsByKeyword(new Page<>(1, 10), "软件").getRecords().forEach(System.out::println);
    }
}
