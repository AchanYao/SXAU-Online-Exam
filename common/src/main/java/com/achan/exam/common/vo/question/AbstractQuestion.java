package com.achan.exam.common.vo.question;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Achan
 * @date 2020/1/29
 */
@Data
@Accessors(chain = true)
public abstract class AbstractQuestion {

    protected Integer id;

    @ApiModelProperty("问题id")
    protected Integer questionId;

    @ApiModelProperty(value = "问题描述")
    @NotNull
    @NotBlank
    protected String questionDescription;

    @ApiModelProperty(value = "参考答案")
    @NotNull
    @NotBlank
    protected String answer;

    @ApiModelProperty(value = "提供该题的学生id")
    protected Integer studentId;

    @ApiModelProperty(value = "录入题库的教师id")
    protected Integer teacherId;

    @ApiModelProperty(value = "章节id")
    @NotNull
    protected Integer chapterId;

    @ApiModelProperty(value = "难度id")
    @NotNull
    protected Integer difficultyId;

    @ApiModelProperty(value = "类型id")
    @NotNull
    protected Integer typeId;
}
