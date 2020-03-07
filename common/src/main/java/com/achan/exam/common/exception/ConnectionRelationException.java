package com.achan.exam.common.exception;

import com.achan.exam.common.vo.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 关联关系异常
 * @author Achan
 * @date 2020/1/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConnectionRelationException extends ExamException {

    private Object[] objects;

    public ConnectionRelationException(Throwable cause, Object... objects) {
        super(cause, ResultCodeEnum.CONNECTION_RELATION_ERROR);
        this.objects = objects;
    }

    public ConnectionRelationException() {
        super(ResultCodeEnum.CONNECTION_RELATION_ERROR);
    }
}
