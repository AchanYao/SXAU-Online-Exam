package com.achan.exam.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 编程题实体
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProgramQuestions对象", description="编程题实体")
public class ProgramQuestions implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT_UPDATE, update = "now()")
    private Date modifyTime;

    @TableField(fill = FieldFill.INSERT, update = "now()")
    private Date createTime;

    @ApiModelProperty(value = "题目描述")
    private String description;

    @ApiModelProperty(value = "参考答案")
    private Integer answerId;

    @ApiModelProperty(value = "提供该题的学生id")
    private Integer studentId;

    @ApiModelProperty(value = "加入该题的教师id")
    private Integer teacherId;

    private Integer questionId;


}
