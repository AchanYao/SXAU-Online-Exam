package com.achan.exam.commons.service;

import com.achan.exam.common.service.impl.TeacherGroupServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Achan
 * @date 2020/1/25
 */
@SpringBootTest
public class TeacherGroupServiceTest {

    @Autowired
    private TeacherGroupServiceImpl teacherGroupService;

    public void teacherGroupOverviewTest() {
        System.out.println(teacherGroupService.teacherGroupOverview(1));
    }
}
