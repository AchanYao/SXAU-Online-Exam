package com.achan.exam.common.dto.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author Achan
 * @date 2020/3/14
 */
@Data
@ApiModel("TeacherDTO")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TeacherDetails {
    private Integer id;

    private Date createTime;

    private Date modifyTime;

    @ApiModelProperty(value = "教师号")
    @NotBlank
    private String number;

    @ApiModelProperty(value = "教师姓名")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "电子邮件")
    private String email;

    @ApiModelProperty(value = "是否启用")
    private String enable;
}
