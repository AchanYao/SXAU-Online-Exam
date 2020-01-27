package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.StudentAnswer;
import com.achan.exam.common.mapper.StudentAnswerMapper;
import com.achan.exam.common.service.IStudentAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生的答案 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class StudentAnswerServiceImpl extends ServiceImpl<StudentAnswerMapper, StudentAnswer> implements IStudentAnswerService {

}
