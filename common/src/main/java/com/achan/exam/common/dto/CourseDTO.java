package com.achan.exam.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Achan
 * @date 2020/3/14
 */
@Data
public class CourseDTO {

    private Integer id;

    private Date createTime;

    private Date modifyTime;

    @ApiModelProperty(value = "课程名（英文）")
    private String name;

    @ApiModelProperty(value = "课程名")
    private String nameZh;

    @ApiModelProperty(value = "课程编号")
    private String number;

    private Integer teacherGroupId;
}
