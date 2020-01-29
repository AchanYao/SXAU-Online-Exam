package com.achan.exam.qbank.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.entity.QuestionType;
import com.achan.exam.common.entity.Role;
import com.achan.exam.common.vo.enumerate.QuestionTypeEnum;
import io.swagger.annotations.Api;
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
@BaseResponse
@Api("题目类型")
@RequestMapping("/api/question-type")
public class QuestionTypeController {

    @GetMapping("/list")
    @ApiOperation("检索所有问题类型")
    public List<QuestionType> list() {
        QuestionTypeEnum[] types = QuestionTypeEnum.values();
        List<QuestionType> result = new ArrayList<>(types.length);
        for (QuestionTypeEnum type : types) {
            result.add(new QuestionType().setName(type.getName()).setId(type.getId()).setNameZh(type.getNameZh()));
        }
        return result;
    }
}
