package com.achan.exam.common.handler;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.exception.ExamException;
import com.achan.exam.common.vo.R;
import com.achan.exam.common.vo.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @author Achan
 * @date 2020/1/15
 */
@ControllerAdvice(annotations = BaseResponse.class)
@ResponseBody
@Slf4j
public class ExamExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.setResult(ResultCodeEnum.UNKNOWN_REASON).message(e.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public R handleSqlException(SQLException e) {
        log.error(e.getMessage(), e);
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(RuntimeException.class)
    public R handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return R.setResult(ResultCodeEnum.UNKNOWN_REASON).message(e.getMessage());
    }

    @ExceptionHandler(ExamException.class)
    public R handleExamException(ExamException e) {
        log.error(e.getMessage(), e);
        return R.setResult(e.getResultCode());
    }
}
