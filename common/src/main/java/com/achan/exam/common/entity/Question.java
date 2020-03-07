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

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 问题实体
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Question对象", description="问题实体")
public class Question implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT, update = "now()")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE, update = "now()")
    private Date modifyTime;

    @ApiModelProperty(value = "类型id")
    private Integer typeId;

    @ApiModelProperty(value = "对应章节id")
    private Integer chapterId;

    @ApiModelProperty(value = "题目简介")
    private String description;

    @ApiModelProperty(value = "难度等级")
    private Integer difficultyId;

    @ApiModelProperty(value = "提供该题的学生id")
    private Integer modifyStudentId;

    @ApiModelProperty(value = "录入题库的教师id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer modifyUserId;
}
