package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.ProgramQuestion;
import com.achan.exam.common.mapper.ProgramQuestionMapper;
import com.achan.exam.common.service.IProgramQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 编程题实体 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class ProgramQuestionServiceImpl extends ServiceImpl<ProgramQuestionMapper, ProgramQuestion> implements IProgramQuestionService {

}
