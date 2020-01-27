package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.Classroom;
import com.achan.exam.common.mapper.ClassroomMapper;
import com.achan.exam.common.service.IClassroomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教室表 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom> implements IClassroomService {

}
