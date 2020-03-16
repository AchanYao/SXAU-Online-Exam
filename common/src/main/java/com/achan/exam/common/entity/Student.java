package com.achan.exam.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Student对象", description="学生表")
public class Student implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT, update = "now()")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE, update = "now()")
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
    @NotNull
    private Integer clazzId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "电子邮件")
    private String email;
}
