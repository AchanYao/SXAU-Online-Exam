package com.achan.exam.admin.controller;

import com.achan.exam.admin.vo.TeacherGroupDetails;
import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.dto.group.TeacherGroupOverview;
import com.achan.exam.common.entity.Teacher;
import com.achan.exam.common.entity.TeacherGroup;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Achan
 * @date 2020/1/16
 */
@RestController
@Api("教师组操作")
@BaseResponse
@RequestMapping("/teacher-groups")
@PreAuthorize("hasRole('admin')")
public class TeacherGroupController {

    @Autowired
    private TeacherGroupServiceImpl teacherGroupService;
    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private TeacherMidGroupServiceImpl teacherMidGroupService;

    @GetMapping("/{id}")
    @ApiOperation("显示教师组详细信息")
    public TeacherGroupDetails getTeacherGroup(@PathVariable int id) {
        List<Teacher> teachers = new ArrayList<>(0);
        teacherMidGroupService.list(new QueryWrapper<TeacherMidGroup>()
                .lambda()
                .eq(TeacherMidGroup::getTeacherGroupId, id)).forEach(teacherMidGroup -> teachers.add(teacherService.getById(teacherMidGroup.getTeacherId())));
        return new TeacherGroupDetails()
                .setTeacherGroupOverview(teacherGroupService.teacherGroupOverview(id))
                .setTeachers(teachers);
    }

    @GetMapping("/one")
    @ApiOperation("跟据课程id查找教师组")
    public TeacherGroup findGroupByCourseId(@RequestParam(name = "course-id") int courseId) {
        return teacherGroupService.getOne(new QueryWrapper<TeacherGroup>().lambda().eq(TeacherGroup::getCourseId, courseId));
    }

    @PutMapping("/{groupId}/principal/{teacherId}")
    @ApiOperation("设置教师组组长")
    public Teacher setPrincipal(@PathVariable int groupId, @PathVariable int teacherId) {
        Teacher teacher = teacherService.getById(teacherId);
        TeacherGroup teacherGroup = teacherGroupService.getById(groupId);
        teacherGroup.setPrincipalId(teacher.getId());
        return teacher;
    }

    @PutMapping("/update")
    @ApiOperation("更新课程组")
    public boolean updateTeacherGroupDetail(@RequestBody @Validated TeacherGroupDetails teacherGroupDetails) {
        TeacherGroup teacherGroup = new TeacherGroup()
                .setId(teacherGroupDetails.getTeacherGroupOverview().getId())
                .setPrincipalId(teacherGroupDetails.getTeacherGroupOverview().getPrincipalId())
                .setCourseId(teacherGroupDetails.getTeacherGroupOverview().getCourseId())
                .setNumber(teacherGroupDetails.getTeacherGroupOverview().getNumber());
        List<Teacher> teachers = teacherGroupDetails.getTeachers();
        List<TeacherMidGroup> teacherMidGroupList = new ArrayList<>(teachers.size());
        teacherMidGroupService.remove(new QueryWrapper<TeacherMidGroup>().lambda().eq(TeacherMidGroup::getTeacherGroupId, teacherGroup.getId()));
        teachers.forEach(teacher -> teacherMidGroupList.add(new TeacherMidGroup().setTeacherId(teacher.getId()).setTeacherGroupId(teacherGroup.getId())));
        return teacherGroupService.updateById(teacherGroup) && teacherMidGroupService.saveBatch(teacherMidGroupList, teacherMidGroupList.size());
    }

    @PostMapping("/new")
    @ApiOperation("新增课程组")
    public boolean addTeacherGroup(@RequestBody @Validated TeacherGroupDetails teacherGroupDetails) {
        TeacherGroup teacherGroup = new TeacherGroup()
                .setPrincipalId(teacherGroupDetails.getTeacherGroupOverview().getPrincipalId())
                .setCourseId(teacherGroupDetails.getTeacherGroupOverview().getCourseId())
                .setNumber(teacherGroupDetails.getTeacherGroupOverview().getNumber());
        return teacherGroupService.saveTeacherGroupDetails(teacherGroup, teacherGroupDetails.getTeachers());
    }

    @GetMapping("/page")
    @ApiOperation("分页检索课程组")
    public Page<TeacherGroupOverview> page(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return teacherGroupService.pageOverview(new Page<>(page, size));
        } else {
            return teacherGroupService.pageOverviewWithKeyword(new Page<>(page, size), keyword);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除指定课程组")
    public boolean delete(@PathVariable int id) {
        return teacherGroupService.removeById(id);
    }
}
