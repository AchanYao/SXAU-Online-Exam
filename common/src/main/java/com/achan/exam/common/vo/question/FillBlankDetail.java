package com.achan.exam.common.vo.question;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Achan
 * @date 2020/3/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class FillBlankDetail extends AbstractQuestion {

    @ApiModelProperty("填空题应填空的个数")
    private Integer blankCount;
}
