package com.achan.exam.common.dto.student;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author Achan
 * @date 2020/1/20
 */
@Data
@ApiModel("学生详细信息封装")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StudentDetails {

    private Integer id;

    private Date createTime;

    private Date modifyTime;

    @ApiModelProperty(value = "学号")
    @NotBlank
    private String number;

    @ApiModelProperty(value = "学生姓名")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "所在班级id")
    private Integer clazzId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "电子邮件")
    private String email;

    @ApiModelProperty(value = "班级名")
    private String clazzName;

    @ApiModelProperty(value = "是否启用")
    private String enable;
}
