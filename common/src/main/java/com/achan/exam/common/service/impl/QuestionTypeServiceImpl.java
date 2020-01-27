package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.QuestionType;
import com.achan.exam.common.mapper.QuestionTypeMapper;
import com.achan.exam.common.service.IQuestionTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问题类型 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class QuestionTypeServiceImpl extends ServiceImpl<QuestionTypeMapper, QuestionType> implements IQuestionTypeService {

}
