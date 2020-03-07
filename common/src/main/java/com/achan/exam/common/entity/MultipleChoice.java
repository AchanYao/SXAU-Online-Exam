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
 * 单项选择题
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MultipleChoice对象", description="单项选择题")
public class MultipleChoice implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer questionId;

    @TableField(fill = FieldFill.INSERT, update = "now()")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE, update = "now()")
    private Date modifyTime;

    @ApiModelProperty(value = "选项A")
    private String optionA;

    @ApiModelProperty(value = "选项B")
    private String optionB;

    @ApiModelProperty(value = "选项C")
    private String optionC;

    @ApiModelProperty(value = "选项D")
    private String optionD;

}
