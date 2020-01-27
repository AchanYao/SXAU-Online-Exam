package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.Course;
import com.achan.exam.common.mapper.CourseMapper;
import com.achan.exam.common.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程实体 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

}
