package com.achan.exam.qbank.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.entity.Answer;
import com.achan.exam.common.service.impl.AnswerServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Achan
 * @date 2020/4/11
 */
@Api("答案控制器")
@RestController
@BaseResponse
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerServiceImpl answerService;

    @GetMapping("/question/{id}")
    @ApiOperation("跟据问题id获取答案")
    public Answer getAnswerByQuestion(@PathVariable(name = "id") int questionId) {
        return answerService.getOne(new QueryWrapper<Answer>().lambda().eq(Answer::getQuestionId, questionId));
    }
}
