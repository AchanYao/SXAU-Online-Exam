package com.achan.exam.common.exception;

import com.achan.exam.common.vo.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Achan
 * @date 2020/1/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataSaveException extends ExamException {

    private Object object;

    public DataSaveException(Object object) {
        super(ResultCodeEnum.DATA_SAVE_ERROR);
        this.object = object;
    }

    public DataSaveException() {
        super(ResultCodeEnum.DATA_SAVE_ERROR);
    }
}
