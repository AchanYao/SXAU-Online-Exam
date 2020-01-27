package com.achan.exam.common.vo.group;

import com.achan.exam.common.entity.Course;
import com.achan.exam.common.entity.Teacher;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Achan
 * @date 2020/1/25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "教师组信息概览")
public class TeacherGroupOverview {

    private Integer id;

    private Date createTime;

    private Date modifyTime;

    @ApiModelProperty(value = "教师组编号")
    @NotNull
    @NotBlank
    private String number;

    @ApiModelProperty(value = "负责教师id")
    @NotNull
    private Integer principalId;

    @ApiModelProperty(value = "负责教师名")
    private String principalName;

    @ApiModelProperty(value = "负责课程id")
    @NotNull
    private Integer courseId;

    @ApiModelProperty(value = "负责课程名（中）")
    private String courseNameZh;

    @ApiModelProperty(value = "负责课程名")
    private String courseName;
}
