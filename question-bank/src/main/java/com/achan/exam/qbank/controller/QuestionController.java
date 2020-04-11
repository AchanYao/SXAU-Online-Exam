package com.achan.exam.qbank.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.dto.question.*;
import com.achan.exam.common.entity.*;
import com.achan.exam.common.service.impl.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
@Api("题库控制器")
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private MultipleChoiceServiceImpl multipleChoiceService;
    @Autowired
    private FillBlankQuestionServiceImpl fillBlankQuestionService;
    @Autowired
    private TOrFQuestionServiceImpl tOrFQuestionService;
    @Autowired
    private SubjectiveQuestionServiceImpl subjectiveQuestionService;
    @Autowired
    private ProgramQuestionServiceImpl programQuestionService;
    @Autowired
    private ProgramInputServiceImpl programInputService;

    private static AbstractQuestion fillQuestion(AbstractQuestion q, Question question) {
        q.setQuestionId(question.getId())
                .setQuestionDescription(question.getDescription())
                .setChapterId(question.getChapterId())
                .setDifficultyId(question.getDifficultyId())
                .setTypeId(question.getTypeId());
        return q;
    }

    public static Question getQuestionByDetail(AbstractQuestion abstractQuestion) {
        return new Question()
                .setChapterId(abstractQuestion.getChapterId())
                .setDifficultyId(abstractQuestion.getDifficultyId())
                .setDescription(abstractQuestion.getQuestionDescription())
                .setModifyStudentId(abstractQuestion.getStudentId())
                .setModifyUserId(abstractQuestion.getTeacherId())
                .setTypeId(abstractQuestion.getTypeId());
    }

    public static Answer getAnswerByDetail(AbstractQuestion abstractQuestion) {
        return new Answer()
                .setQuestionId(abstractQuestion.getQuestionId());
    }

    @ApiOperation("检索题库")
    @GetMapping("/page")
    public Page<Question> questionPage(@RequestParam(required = false, defaultValue = "1") int page,
                                       @RequestParam(required = false, defaultValue = "10") int size,
                                       @RequestParam(required = false) String keyword,
                                       @RequestParam(required = false, name = "t") Integer type,
                                       @RequestParam(required = false, name = "d") Integer difficult,
                                       @RequestParam(required = false, name = "c") Integer chapter) {
        Page<Question> pageE = new Page<>(page, size);
        LambdaQueryWrapper<Question> questionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (difficult != null) {
            questionLambdaQueryWrapper = questionLambdaQueryWrapper.eq(Question::getDifficultyId, difficult);
        }
        if (type != null) {
            questionLambdaQueryWrapper = questionLambdaQueryWrapper.eq(Question::getTypeId, type);
        }
        if (keyword != null && !keyword.isBlank()) {
            questionLambdaQueryWrapper = questionLambdaQueryWrapper.like(Question::getDescription, keyword);
        }
        if (chapter != null) {
            questionLambdaQueryWrapper = questionLambdaQueryWrapper.eq(Question::getChapterId, chapter);
        }
        return questionService.page(pageE, questionLambdaQueryWrapper);
    }

    @ApiOperation("查看问题")
    @GetMapping("/{id}")
    public AbstractQuestion question(@PathVariable int id) {
        Question question = questionService.getById(id);
        Answer answer = answerService.getOne(new LambdaQueryWrapper<Answer>().eq(Answer::getQuestionId, question.getId()));
        switch (question.getTypeId()) {
            case 1: {
                // 单选题
                MultipleChoiceDetail multipleChoiceDetail = (MultipleChoiceDetail) fillQuestion(new MultipleChoiceDetail(), question);
                MultipleChoice multipleChoice = multipleChoiceService.getOne(new QueryWrapper<MultipleChoice>()
                        .lambda().eq(MultipleChoice::getQuestionId, question.getId()));
                multipleChoiceDetail
                        .setOptionA(multipleChoice.getOptionA())
                        .setOptionB(multipleChoice.getOptionB())
                        .setOptionC(multipleChoice.getOptionC())
                        .setOptionD(multipleChoice.getOptionD());
                return multipleChoiceDetail;
            }
            case 2: {
                // 填空题
                FillBlankDetail fillBlankDetail = (FillBlankDetail) fillQuestion(new FillBlankDetail(), question);
                FillBlankQuestion fillBlankQuestion = fillBlankQuestionService.getOne(new QueryWrapper<FillBlankQuestion>()
                        .lambda().eq(FillBlankQuestion::getQuestionId, question.getId()));
                fillBlankDetail.setBlankCount(fillBlankQuestion.getBlankCount());
                return fillBlankDetail;
            }
            case 3: {
                // 判断题
                return (TrueOrFalseDetail) fillQuestion(new TrueOrFalseDetail(), question);
            }
            case 4: {
                // 主观题
                SubjectiveQuestionDetail subjectiveQuestionDetail = (SubjectiveQuestionDetail) fillQuestion(new SubjectiveQuestionDetail(), question);
                SubjectiveQuestion subjectiveQuestion = subjectiveQuestionService.getOne(new QueryWrapper<SubjectiveQuestion>()
                        .lambda().eq(SubjectiveQuestion::getQuestionId, question.getId()));
                subjectiveQuestionDetail.setTeacherId(subjectiveQuestion.getTeacherId())
                        .setStudentId(subjectiveQuestion.getStudentId());
                return subjectiveQuestionDetail;
            }
            case 5: {
                // 编程题
                ProgramQuestionDetail programQuestionDetail = (ProgramQuestionDetail) fillQuestion(new ProgramQuestionDetail(), question);
                ProgramQuestion programQuestion = programQuestionService.getOne(new QueryWrapper<ProgramQuestion>()
                        .lambda().eq(ProgramQuestion::getQuestionId, question.getId()));
                programQuestionDetail
                        .setInputs(programInputService.list(new QueryWrapper<ProgramInput>()
                                .lambda().eq(ProgramInput::getProgramId, programQuestion.getId())));
                return programQuestionDetail;
            }
            default:
                throw new RuntimeException("未知类型id：" + question.getTypeId());
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除问题")
    public boolean delete(@PathVariable int id) {
        return questionService.removeById(id);
    }

    @GetMapping("/count")
    @ApiOperation("题目数量")
    public int count() {
        return questionService.count();
    }
}
