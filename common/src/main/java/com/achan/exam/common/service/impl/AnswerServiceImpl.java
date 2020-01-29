package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.Answer;
import com.achan.exam.common.mapper.AnswerMapper;
import com.achan.exam.common.service.IAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参考答案 服务实现类
 * </p>
 *
 * @author Achan
 * @date 2020/1/29
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements IAnswerService {
}
