package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.Exam;
import com.achan.exam.common.mapper.ExamMapper;
import com.achan.exam.common.service.IExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 考试表 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements IExamService {

}
