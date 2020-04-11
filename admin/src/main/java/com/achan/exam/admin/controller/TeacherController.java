package com.achan.exam.admin.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.dto.teacher.TeacherDetails;
import com.achan.exam.common.entity.Teacher;
import com.achan.exam.common.entity.TeacherMidGroup;
import com.achan.exam.common.service.impl.TeacherGroupServiceImpl;
import com.achan.exam.common.service.impl.TeacherMidGroupServiceImpl;
import com.achan.exam.common.service.impl.TeacherServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Achan
 * @date 2020/1/15
 */
@RestController
@RequestMapping("/api/teachers")
@Api(value = "教师操作")
@BaseResponse
@PreAuthorize("hasRole('admin')")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private TeacherGroupServiceImpl teacherGroupService;
    @Autowired
    private TeacherMidGroupServiceImpl teacherMidGroupService;

    @GetMapping("/page")
    @ApiOperation("分页查询教师")
    public Page<TeacherDetails> page(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isBlank()) {
            return teacherService.getTeacherWithDetailsByKeyword(new Page<>(page, size), keyword);
        } else {
            return teacherService.getTeacherWithDetails(new Page<>(page, size));
        }
    }

    @GetMapping("/groups/{groupId}/teachers")
    @ApiOperation("教师组的所有教师")
    public List<Teacher> listByGroup(@PathVariable int groupId) {
        List<Teacher> teachers = new ArrayList<>(0);
        teacherMidGroupService.list(new QueryWrapper<TeacherMidGroup>()
                .lambda()
                .eq(TeacherMidGroup::getTeacherGroupId, groupId)).forEach(teacherMidGroup -> teachers.add(teacherService.getById(teacherMidGroup.getTeacherId())));
        return teachers;
    }

    @GetMapping("/one/{number}")
    @ApiOperation("检索指定教师")
    public Teacher getTeacherByNumber(@PathVariable @Validated @NotBlank @NotNull String number) {
        return teacherService.getOne(new QueryWrapper<Teacher>().lambda().eq(Teacher::getNumber, number));
    }

    @GetMapping("/groups/{id}/principal")
    @ApiOperation("检索教师组组长")
    public Teacher getTeacherGroupPrincipal(@PathVariable int id) {
        return teacherService.getById(teacherGroupService.getById(id).getPrincipalId());
    }

    @PutMapping("/update")
    @ApiOperation("更新")
    public boolean updateTeacher(@Validated @RequestBody Teacher teacher) {
        return teacherService.updateById(teacher);
    }

    @GetMapping("/count")
    @ApiOperation("获取教师数量")
    public int teacherCount() {
        return teacherService.count();
    }

    @GetMapping("/list")
    @ApiOperation("获取所有教师")
    public List<Teacher> teachers() {
        return teacherService.list();
    }
}
