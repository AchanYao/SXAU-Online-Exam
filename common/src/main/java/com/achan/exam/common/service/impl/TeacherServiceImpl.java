package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.Teacher;
import com.achan.exam.common.mapper.TeacherMapper;
import com.achan.exam.common.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教师表 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
