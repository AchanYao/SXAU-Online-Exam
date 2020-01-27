package com.achan.exam.admin.vo;

import com.achan.exam.common.entity.Teacher;
import com.achan.exam.common.entity.TeacherGroup;
import com.achan.exam.common.vo.group.TeacherGroupOverview;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Achan
 * @date 2020/1/16
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "教师组详细信息封装")
public class TeacherGroupDetails {
    private TeacherGroupOverview teacherGroupOverview;
    private List<Teacher> teachers;
}
