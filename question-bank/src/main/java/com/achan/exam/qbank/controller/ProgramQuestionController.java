package com.achan.exam.qbank.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.dto.question.ProgramQuestionDetail;
import com.achan.exam.common.entity.Answer;
import com.achan.exam.common.entity.ProgramInput;
import com.achan.exam.common.entity.ProgramQuestion;
import com.achan.exam.common.entity.Question;
import com.achan.exam.common.exception.DataSaveException;
import com.achan.exam.common.exception.ExamException;
import com.achan.exam.common.service.impl.AnswerServiceImpl;
import com.achan.exam.common.service.impl.ProgramInputServiceImpl;
import com.achan.exam.common.service.impl.ProgramQuestionServiceImpl;
import com.achan.exam.common.service.impl.QuestionServiceImpl;
import com.achan.exam.common.vo.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Achan
 * @date 2020/3/17
 */
@RestController
@BaseResponse
@RequestMapping("/questions/pq")
@Api("编程题控制器")
@PreAuthorize("hasRole('teacher')")
public class ProgramQuestionController {
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private ProgramQuestionServiceImpl programQuestionService;
    @Autowired
    private ProgramInputServiceImpl programInputService;

    @PostMapping("/add")
    @ApiOperation("新增单选问题")
    public ProgramQuestionDetail add(@RequestBody @Validated ProgramQuestionDetail programQuestionDetail) {
        Question question = QuestionController.getQuestionByDetail(programQuestionDetail);
        questionService.save(question);
        Answer answer = QuestionController.getAnswerByDetail(programQuestionDetail.setQuestionId(question.getId()));
        answerService.save(answer);
        ProgramQuestion programQuestion = new ProgramQuestion()
                .setQuestionId(question.getId());
        programQuestionDetail.setQuestionId(question.getId());
        if (!programQuestionService.save(programQuestion)) {
            throw new DataSaveException();
        }
        List<ProgramInput> inputs = programQuestionDetail.getInputs();
        inputs.forEach(programInput -> programInput.setProgramId(programQuestion.getId()));
        if (!programInputService.saveBatch(inputs)) {
            throw new DataSaveException();
        }
        return programQuestionDetail;
    }

    @PutMapping("/change")
    @ApiOperation("问题修改")
    public ProgramQuestionDetail change(@RequestBody @Validated ProgramQuestionDetail programQuestionDetail) {
        Question question = QuestionController.getQuestionByDetail(programQuestionDetail);
        questionService.update(question, new QueryWrapper<Question>().lambda().eq(Question::getId, programQuestionDetail.getQuestionId()));
        Answer answer = QuestionController.getAnswerByDetail(programQuestionDetail);
        answerService.update(answer, new QueryWrapper<Answer>().lambda().eq(Answer::getQuestionId, question.getId()));
        updateInputs(programQuestionDetail);
        return programQuestionDetail;
    }

    private void updateInputs(ProgramQuestionDetail programQuestionDetail) {
        if (programQuestionDetail.getInputs() == null) {
            // 未包含输入时不更新
            return;
        }
        ProgramQuestion programQuestion = programQuestionService.getOne(new QueryWrapper<ProgramQuestion>().lambda().eq(ProgramQuestion::getQuestionId, programQuestionDetail.getQuestionId()));
        if (!programInputService.remove(new QueryWrapper<ProgramInput>().lambda().eq(ProgramInput::getProgramId, programQuestion.getId()))) {
            throw new ExamException(ResultCodeEnum.BAD_SQL_GRAMMAR);
        }
        if (!programInputService.saveBatch(programQuestionDetail.getInputs())) {
            throw new DataSaveException();
        }
    }
}
