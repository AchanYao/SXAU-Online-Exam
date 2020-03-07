package com.achan.exam.common.vo.question;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Achan
 * @date 2020/1/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class MultipleChoiceDetail extends AbstractQuestion {

    @ApiModelProperty(value = "选项A")
    private String optionA;

    @ApiModelProperty(value = "选项B")
    private String optionB;

    @ApiModelProperty(value = "选项C")
    private String optionC;

    @ApiModelProperty(value = "选项D")
    private String optionD;

}
