package com.achan.exam.admin.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.entity.Student;
import com.achan.exam.common.service.impl.StudentServiceImpl;
import com.achan.exam.common.util.ApplicationContextUtil;
import com.achan.exam.common.vo.student.StudentDetails;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Achan
 * @since 2020/1/14
 */
@RestController
@RequestMapping("/api/students")
@Api("学生增删改查")
@BaseResponse
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/page")
    @ApiOperation("学生分页")
    public Page<StudentDetails> getAllStudents(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isBlank()) {
            return studentService.getStudentWithDetailsByKeyword(new Page<>(page, size), keyword);
        } else {
            return studentService.getStudentWithDetails(new Page<>(page, size));
        }
    }

    @GetMapping("/one/{number}")
    @ApiOperation("检索指定学号学生")
    public Student getStudentByNumber(@PathVariable String number) {
        Student student = studentService.getOne(new QueryWrapper<Student>().lambda().eq(Student::getNumber, number));
        return student;
    }

    @GetMapping("/classes/{classId}")
    @ApiOperation("检索指定班级的所有学生")
    public List<Student> getStudentByClazz(@PathVariable int classId) {
        List<Student> students = studentService.list(new QueryWrapper<Student>().lambda().eq(Student::getClazzId, classId));
        return students;
    }

    @PostMapping("/update/{id}")
    @ApiOperation("修改学生信息")
    public boolean addStudent(@RequestBody Student student) {
        return studentService.updateById(student);
    }

    @GetMapping("/count")
    @ApiOperation("获取学生数量")
    public int studentsCount() {
        return studentService.count();
    }
}
