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
 * 判断题实体
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FillBlankQuestion对象", description="填空题实体")
public class FillBlankQuestion implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT, update = "now()")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE, update = "now()")
    private Date modifyTime;

    @ApiModelProperty(value = "题目id")
    private Integer questionId;

    @ApiModelProperty(value = "参考答案")
    private Integer answerId;

    @ApiModelProperty(value = "题目描述")
    private String description;

    @ApiModelProperty(value = "本题应填答案个数")
    private Integer blankCount;


}
