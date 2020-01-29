package com.achan.exam.qbank.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.entity.Answer;
import com.achan.exam.common.entity.MultipleChoice;
import com.achan.exam.common.entity.Question;
import com.achan.exam.common.service.impl.AnswerServiceImpl;
import com.achan.exam.common.service.impl.MultipleChoiceServiceImpl;
import com.achan.exam.common.service.impl.QuestionServiceImpl;
import com.achan.exam.common.vo.question.MultipleChoiceDetail;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Achan
 * @date 2020/1/29
 */
@RequestMapping("/api/questions")
@RestController
@BaseResponse
@Api("题库问题")
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private MultipleChoiceServiceImpl multipleChoiceService;

    @ApiOperation("检索题库")
    @GetMapping("/page")
    public Page<Question> questionPage(@RequestParam(required = false, defaultValue = "1") int page,
                                       @RequestParam(required = false, defaultValue = "10") int size,
                                       @RequestParam(required = false, name = "t") Integer type,
                                       @RequestParam(required = false, name = "d") Integer difficult) {
        Page pageE = new Page(page, size);
        LambdaQueryWrapper<Question> questionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (difficult != null) {
            questionLambdaQueryWrapper.eq(Question::getDifficultyId, difficult);
        }
        if (type != null) {
            questionLambdaQueryWrapper.eq(Question::getTypeId, type);
        }
        return questionService.page(pageE, questionLambdaQueryWrapper);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除问题")
    public boolean delete(@PathVariable int id) {
        return questionService.removeById(id);
    }

    @PostMapping("/mc/add")
    @ApiOperation("新增单选问题")
    public boolean addMultipleChoice(@RequestBody MultipleChoiceDetail multipleChoiceDetail) {
        Question question = new Question()
                .setChapterId(multipleChoiceDetail.getChapterId())
                .setDifficultyId(multipleChoiceDetail.getDifficultyId())
                .setDescription(multipleChoiceDetail.getDescription())
                .setTypeId(multipleChoiceDetail.getTypeId());
        questionService.save(question);
        Answer answer = new Answer()
                .setContent(multipleChoiceDetail.getAnswer())
                .setQuestionId(question.getId());
        answerService.save(answer);
        MultipleChoice multipleChoice = new MultipleChoice()
                .setQuestionId(question.getId())
                .setOptionA(multipleChoiceDetail.getOptionA())
                .setOptionB(multipleChoiceDetail.getOptionB())
                .setOptionC(multipleChoiceDetail.getOptionC())
                .setOptionD(multipleChoiceDetail.getOptionD());
        return multipleChoiceService.save(multipleChoice);
    }

}
