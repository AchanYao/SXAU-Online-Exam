package com.achan.exam.qbank.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.dto.question.FillBlankDetail;
import com.achan.exam.common.entity.Answer;
import com.achan.exam.common.entity.FillBlankQuestion;
import com.achan.exam.common.entity.Question;
import com.achan.exam.common.exception.DataSaveException;
import com.achan.exam.common.service.impl.AnswerServiceImpl;
import com.achan.exam.common.service.impl.FillBlankQuestionServiceImpl;
import com.achan.exam.common.service.impl.QuestionServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Achan
 * @date 2020/3/17
 */
@RestController
@BaseResponse
@RequestMapping("/questions/fb")
@Api("填空题控制器")
@PreAuthorize("hasRole('teacher')")
public class FillBlankController {

    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private FillBlankQuestionServiceImpl fillBlankQuestionService;

    @PostMapping("/add")
    @ApiOperation("新增单选问题")
    public FillBlankDetail addMultipleChoice(@RequestBody @Validated FillBlankDetail fillBlankDetail) {
        Question question = QuestionController.getQuestionByDetail(fillBlankDetail);
        questionService.save(question);
        Answer answer = QuestionController.getAnswerByDetail(fillBlankDetail.setQuestionId(question.getId()));
        answerService.save(answer);
        FillBlankQuestion fillBlankQuestion = new FillBlankQuestion()
                .setQuestionId(question.getId())
                .setBlankCount(fillBlankDetail.getBlankCount());
        if (!fillBlankQuestionService.save(fillBlankQuestion)) {
            throw new DataSaveException();
        }
        return (FillBlankDetail) fillBlankDetail.setQuestionId(question.getId());
    }

    @PutMapping("/change")
    @ApiOperation("问题修改")
    public FillBlankDetail change(@RequestBody @Validated FillBlankDetail fillBlankDetail) {
        Question question = QuestionController.getQuestionByDetail(fillBlankDetail);
        questionService.update(question, new QueryWrapper<Question>().lambda().eq(Question::getId, fillBlankDetail.getQuestionId()));
        Answer answer = QuestionController.getAnswerByDetail(fillBlankDetail);
        answerService.update(answer, new QueryWrapper<Answer>().lambda().eq(Answer::getQuestionId, question.getId()));
        FillBlankQuestion fillBlankQuestion = new FillBlankQuestion()
                .setQuestionId(question.getId())
                .setBlankCount(fillBlankDetail.getBlankCount());
        if (!fillBlankQuestionService.save(fillBlankQuestion)) {
            throw new DataSaveException();
        }
        return (FillBlankDetail) fillBlankDetail.setQuestionId(question.getId());
    }
}
