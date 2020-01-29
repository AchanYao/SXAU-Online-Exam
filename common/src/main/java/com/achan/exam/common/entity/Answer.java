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
 * 参考答案
 * </p>
 *
 * @author Achan
 * @date 2020/1/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Answer对象", description="参考答案")
public class Answer implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT, update = "now()")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE, update = "now()")
    private Date modifyTime;

    @ApiModelProperty("问题id")
    private Integer questionId;

    @ApiModelProperty("答案内容")
    private String content;
}
