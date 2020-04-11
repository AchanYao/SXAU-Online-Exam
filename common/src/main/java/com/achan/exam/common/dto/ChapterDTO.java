package com.achan.exam.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Achan
 * @date 2020/3/30
 */
@Data
@ApiModel("ChapterDTO")
public class ChapterDTO {

    private Integer id;
    @ApiModelProperty("章节名称")
    private String name;
    @ApiModelProperty("章节介绍")
    private String description;
    @ApiModelProperty("问题数量")
    @TableField(value = "question_count")
    private Integer questionCount;
}
