package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.FillBlankQuestion;
import com.achan.exam.common.mapper.FillBlankQuestionMapper;
import com.achan.exam.common.service.IFillBlankQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 填空题表 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-29
 */
@Service
public class FillBlankQuestionServiceImpl extends ServiceImpl<FillBlankQuestionMapper, FillBlankQuestion> implements IFillBlankQuestionService {
}
