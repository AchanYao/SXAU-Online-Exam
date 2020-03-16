package com.achan.exam.admin.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.entity.Course;
import com.achan.exam.common.entity.TeacherGroup;
import com.achan.exam.common.service.impl.CourseServiceImpl;
import com.achan.exam.common.service.impl.TeacherGroupServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Achan
 * @date 2020/1/22
 */
@BaseResponse
@RestController
@Slf4j
@Api("课程操作")
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private TeacherGroupServiceImpl teacherGroupService;

    @GetMapping("/page")
    @ApiOperation("分页检索课程")
    public Page<Course> coursePage(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return courseService.page(new Page<>(page, size));
        } else {
            return courseService.page(new Page<>(page, size),
                    new QueryWrapper<Course>()
                            .lambda()
                            .like(Course::getNameZh, keyword)
                            .or()
                            .like(Course::getName, keyword)
                            .or()
                            .like(Course::getNumber, keyword)
            );
        }
    }

    @PostMapping("/add")
    @ApiOperation("增加课程")
    public Course addCourse(@RequestBody Course course) {
        courseService.save(course);
        return course;
    }

    @PutMapping("/update")
    @ApiOperation("修改课程")
    public Course updateCourse(@RequestBody Course course) {
        courseService.updateById(course);
        return course;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除课程")
    public boolean deleteCourse(@PathVariable int id) {
        teacherGroupService.remove(new QueryWrapper<TeacherGroup>().lambda().eq(TeacherGroup::getCourseId, id));
        return courseService.removeById(id);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取指定课程信息")
    public Course getCourse(@PathVariable int id) {
        return courseService.getById(id);
    }

    @GetMapping("/count")
    @ApiOperation("获取系统课程数量")
    public int count() {
        return courseService.count();
    }

    @GetMapping("/{id}/teacher-group")
    @ApiOperation("获取教授该课程的课程组")
    public TeacherGroup teacherGroup(@PathVariable int id) {
        return teacherGroupService.getOne(new QueryWrapper<TeacherGroup>().lambda().eq(TeacherGroup::getCourseId, id));
    }

    @GetMapping("/no-teacher-group")
    @ApiOperation("获取没有设置教师组的课程列表")
    public List<Course> coursesNoTeacherGroup() {
        List<Course> courses = courseService.list();
        List<Course> result = new LinkedList<>();
        courses.forEach(course -> {
            TeacherGroup teacherGroup = teacherGroupService.getOne(
                    new QueryWrapper<TeacherGroup>()
                            .lambda()
                            .eq(TeacherGroup::getCourseId, course.getId())
            );
            if (teacherGroup == null) {
                result.add(course);
            }
        });
        return result;
    }
}
