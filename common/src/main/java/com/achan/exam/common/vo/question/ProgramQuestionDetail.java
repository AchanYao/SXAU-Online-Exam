package com.achan.exam.common.vo.question;

import com.achan.exam.common.entity.ProgramInput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Achan
 * @date 2020/3/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ProgramQuestionDetail extends AbstractQuestion {

    private List<ProgramInput> inputs;
}
