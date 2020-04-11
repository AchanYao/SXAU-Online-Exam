package com.achan.exam.qbank.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.entity.Answer;
import com.achan.exam.common.entity.Question;
import com.achan.exam.common.entity.TOrFQuestion;
import com.achan.exam.common.service.impl.AnswerServiceImpl;
import com.achan.exam.common.service.impl.QuestionServiceImpl;
import com.achan.exam.common.service.impl.TOrFQuestionServiceImpl;
import com.achan.exam.common.dto.question.TrueOrFalseDetail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Achan
 * @date 2020/3/6
 */
@RestController
@RequestMapping("/api/questions/tof")
@BaseResponse
@Api("判断题控制器")
@PreAuthorize("hasRole('teacher')")
public class TrueOrFalseController {

    @Autowired
    private TOrFQuestionServiceImpl tOrFQuestionService;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private AnswerServiceImpl answerService;

    @PostMapping("/add")
    @ApiOperation("新增单选问题")
    public boolean addMultipleChoice(@RequestBody @Validated TrueOrFalseDetail trueOrFalseDetail) {
        Question question = QuestionController.getQuestionByDetail(trueOrFalseDetail);
        questionService.save(question);
        Answer answer = QuestionController.getAnswerByDetail(trueOrFalseDetail.setQuestionId(question.getId()));
        answerService.save(answer);
        TOrFQuestion tOrFQuestion = new TOrFQuestion()
                .setQuestionId(question.getId())
                .setAnswerId(answer.getId())
                .setStudentId(trueOrFalseDetail.getStudentId())
                .setTeacherId(trueOrFalseDetail.getStudentId());
        return tOrFQuestionService.save(tOrFQuestion);
    }

    @PutMapping("/change")
    @ApiOperation("问题修改")
    public boolean change(@RequestBody @Validated TrueOrFalseDetail trueOrFalseDetail) {
        Question question = QuestionController.getQuestionByDetail(trueOrFalseDetail);
        questionService.update(question, new QueryWrapper<Question>()
                .lambda().eq(Question::getId, trueOrFalseDetail.getQuestionId()));
        Answer answer = QuestionController.getAnswerByDetail(trueOrFalseDetail.setQuestionId(question.getId()));
        answerService.update(answer, new QueryWrapper<Answer>()
                .lambda().eq(Answer::getQuestionId, question.getId()));
        TOrFQuestion tOrFQuestion = new TOrFQuestion()
                .setQuestionId(question.getId())
                .setAnswerId(answer.getId())
                .setStudentId(trueOrFalseDetail.getStudentId())
                .setTeacherId(trueOrFalseDetail.getStudentId());
        return tOrFQuestionService.update(tOrFQuestion, new QueryWrapper<TOrFQuestion>().lambda().eq(TOrFQuestion::getQuestionId, question.getId()));
    }
}
