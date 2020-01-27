package com.achan.exam.common.exception;

import com.achan.exam.common.vo.ResultCodeEnum;
import lombok.Data;

/**
 * @author Achan
 * @date 2020/1/15
 */
@Data
public class ExamException extends RuntimeException {

    ResultCodeEnum resultCode;

    public ExamException(Throwable cause, ResultCodeEnum resultCode) {
        super(cause);
        this.resultCode = resultCode;
    }

    public ExamException(ResultCodeEnum resultCode) {
        this.resultCode = resultCode;
    }
}
