package com.achan.exam.qbank.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.entity.QuestionDifficulty;
import com.achan.exam.common.vo.enumerate.QuestionDifficultyEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Achan
 * @date 2020/1/29
 */
@RestController
@RequestMapping("/api/question-difficulty")
@BaseResponse
@ApiOperation("问题难度")
public class QuestionDifficultlyController {

    @GetMapping("/list")
    @ApiOperation("检索所有问题难度")
    public List<QuestionDifficulty> questionDifficulties() {
        QuestionDifficultyEnum[] difficulties = QuestionDifficultyEnum.values();
        List<QuestionDifficulty> result = new ArrayList<>(difficulties.length);
        for (QuestionDifficultyEnum difficultyEnum : difficulties) {
            result.add(new QuestionDifficulty()
                    .setName(difficultyEnum.getName())
                    .setId(difficultyEnum.getId())
                    .setNameZh(difficultyEnum.getNameZh()));
        }
        return result;
    }
}
