package com.achan.exam.qbank.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.entity.Answer;
import com.achan.exam.common.entity.MultipleChoice;
import com.achan.exam.common.entity.Question;
import com.achan.exam.common.service.impl.AnswerServiceImpl;
import com.achan.exam.common.service.impl.MultipleChoiceServiceImpl;
import com.achan.exam.common.service.impl.QuestionServiceImpl;
import com.achan.exam.common.dto.question.MultipleChoiceDetail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Achan
 * @date 2020/2/25
 */
@RestController
@BaseResponse
@RequestMapping("/api/questions/mc")
@Api("单选题控制器")
public class MultipleChoiceController {

    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private MultipleChoiceServiceImpl multipleChoiceService;

    @PostMapping("/add")
    @ApiOperation("新增单选问题")
    public boolean addMultipleChoice(@RequestBody @Validated MultipleChoiceDetail multipleChoiceDetail) {
        Question question = QuestionController.getQuestionByDetail(multipleChoiceDetail);
        questionService.save(question);
        Answer answer = QuestionController.getAnswerByDetail(multipleChoiceDetail.setQuestionId(question.getId()));
        answerService.save(answer);
        MultipleChoice multipleChoice = new MultipleChoice()
                .setQuestionId(question.getId())
                .setOptionA(multipleChoiceDetail.getOptionA())
                .setOptionB(multipleChoiceDetail.getOptionB())
                .setOptionC(multipleChoiceDetail.getOptionC())
                .setOptionD(multipleChoiceDetail.getOptionD());
        return multipleChoiceService.save(multipleChoice);
    }

    @PutMapping("/change")
    @ApiOperation("问题修改")
    public boolean change(@RequestBody @Validated MultipleChoiceDetail multipleChoiceDetail) {
        Question question = QuestionController.getQuestionByDetail(multipleChoiceDetail);
        questionService.update(question, new QueryWrapper<Question>().lambda().eq(Question::getId, multipleChoiceDetail.getQuestionId()));
        Answer answer = QuestionController.getAnswerByDetail(multipleChoiceDetail);
        answerService.update(answer, new QueryWrapper<Answer>().lambda().eq(Answer::getQuestionId, question.getId()));
        MultipleChoice multipleChoice = new MultipleChoice()
                .setOptionA(multipleChoiceDetail.getOptionA())
                .setOptionB(multipleChoiceDetail.getOptionB())
                .setOptionC(multipleChoiceDetail.getOptionC())
                .setOptionD(multipleChoiceDetail.getOptionD());
        return multipleChoiceService.update(multipleChoice, new QueryWrapper<MultipleChoice>().lambda().eq(MultipleChoice::getQuestionId, question.getId()));
    }
}
