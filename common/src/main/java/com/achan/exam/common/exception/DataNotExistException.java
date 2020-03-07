package com.achan.exam.common.exception;

import com.achan.exam.common.vo.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 访问的数据不存在，常用在需要修改对象时却无主键情况
 * @author Achan
 * @date 2020/2/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataNotExistException extends ExamException {

    public DataNotExistException() {
        super(ResultCodeEnum.DATA_NOT_EXISTS);
    }
}
