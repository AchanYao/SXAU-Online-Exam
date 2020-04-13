package com.achan.exam.qbank.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.dto.question.FillBlankDetail;
import com.achan.exam.common.dto.question.SubjectiveQuestionDetail;
import com.achan.exam.common.entity.Answer;
import com.achan.exam.common.entity.FillBlankQuestion;
import com.achan.exam.common.entity.Question;
import com.achan.exam.common.entity.SubjectiveQuestion;
import com.achan.exam.common.exception.DataSaveException;
import com.achan.exam.common.service.impl.AnswerServiceImpl;
import com.achan.exam.common.service.impl.FillBlankQuestionServiceImpl;
import com.achan.exam.common.service.impl.QuestionServiceImpl;
import com.achan.exam.common.service.impl.SubjectiveQuestionServiceImpl;
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
@RequestMapping("/questions/sq")
@Api("主观题控制器")
@PreAuthorize("hasRole('teacher')")
public class SubjectQuestionController {
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private SubjectiveQuestionServiceImpl subjectiveQuestionService;

    @PostMapping("/add")
    @ApiOperation("新增单选问题")
    public SubjectiveQuestionDetail addMultipleChoice(@RequestBody @Validated SubjectiveQuestionDetail subjectiveQuestionDetail) {
        Question question = QuestionController.getQuestionByDetail(subjectiveQuestionDetail);
        questionService.save(question);
        Answer answer = QuestionController.getAnswerByDetail(subjectiveQuestionDetail.setQuestionId(question.getId()));
        answerService.save(answer);
        SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion()
                .setQuestionId(question.getId());
        if (!subjectiveQuestionService.save(subjectiveQuestion)) {
            throw new DataSaveException();
        }
        return (SubjectiveQuestionDetail) subjectiveQuestionDetail.setQuestionId(question.getId());
    }

    @PutMapping("/change")
    @ApiOperation("问题修改")
    public SubjectiveQuestionDetail change(@RequestBody @Validated SubjectiveQuestionDetail subjectiveQuestionDetail) {
        Question question = QuestionController.getQuestionByDetail(subjectiveQuestionDetail);
        questionService.update(question, new QueryWrapper<Question>().lambda().eq(Question::getId, subjectiveQuestionDetail.getQuestionId()));
        Answer answer = QuestionController.getAnswerByDetail(subjectiveQuestionDetail);
        answerService.update(answer, new QueryWrapper<Answer>().lambda().eq(Answer::getQuestionId, question.getId()));
        SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion()
                .setQuestionId(question.getId());
        if (!subjectiveQuestionService.updateById(subjectiveQuestion)) {
            throw new DataSaveException();
        }
        return subjectiveQuestionDetail;
    }
}
