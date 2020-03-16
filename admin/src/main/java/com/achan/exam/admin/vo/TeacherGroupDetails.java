package com.achan.exam.admin.vo;

import com.achan.exam.common.dto.group.TeacherGroupOverview;
import com.achan.exam.common.entity.Teacher;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Achan
 * @date 2020/1/16
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "教师组详细信息封装")
public class TeacherGroupDetails {
    @NotNull
    private TeacherGroupOverview teacherGroupOverview;
    @NotNull
    private List<Teacher> teachers;
}
