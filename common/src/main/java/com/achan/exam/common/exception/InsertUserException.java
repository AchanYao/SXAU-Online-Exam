package com.achan.exam.common.exception;

import com.achan.exam.common.entity.User;
import com.achan.exam.common.vo.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Achan
 * @date 2020/1/16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InsertUserException extends ExamException {

    private User user;

    public InsertUserException(User user) {
        super(ResultCodeEnum.INSERT_USER_ERROR);
        this.user = user;
    }

    public InsertUserException() {
        super(ResultCodeEnum.INSERT_USER_ERROR);
    }
}
