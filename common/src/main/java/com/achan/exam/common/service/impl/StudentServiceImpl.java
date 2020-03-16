package com.achan.exam.common.service.impl;

import com.achan.exam.common.dto.student.StudentDetails;
import com.achan.exam.common.entity.Student;
import com.achan.exam.common.mapper.StudentMapper;
import com.achan.exam.common.service.IStudentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    public Page<StudentDetails> getStudentWithDetails(Page<StudentDetails> page) {
        return this.baseMapper.getStudentWithDetails(page);
    }

    public Page<StudentDetails> getStudentWithDetailsByKeyword(Page<StudentDetails> page, String keyword) {
        return this.baseMapper.getStudentWithDetailsByKeyword(page, keyword);
    }
}
