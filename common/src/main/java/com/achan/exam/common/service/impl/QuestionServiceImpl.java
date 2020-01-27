package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.Question;
import com.achan.exam.common.mapper.QuestionMapper;
import com.achan.exam.common.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问题实体 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}
