package com.achan.exam.common.mapper;

import com.achan.exam.common.dto.teacher.TeacherDetails;
import com.achan.exam.common.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 教师表 Mapper 接口
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    /**
     * 分页获取学生详细信息
     * @param page 分页
     * @return
     */
    @Select("select teacher.id, teacher.create_time, teacher.modify_time, teacher.number, teacher.`name`, teacher.id_number, teacher.phone, teacher.email, teacher.user_id, user.enable from teacher left join user on teacher.user_id = user.id")
    Page<TeacherDetails> getTeacherWithDetails(Page<TeacherDetails> page);

    /**
     * 关键字分页查询
     * @param keyword 关键字
     * @param page 分页
     * @return
     */
    @Select("select teacher.id, teacher.create_time, teacher.modify_time, teacher.number, teacher.`name`, teacher.id_number, teacher.phone, teacher.email, teacher.user_id, user.enable from teacher left join user on teacher.user_id = user.id where teacher.`name` like '%${keyword}%' or teacher.number like '%${keyword}%'")
    Page<TeacherDetails> getTeacherWithDetailsByKeyword(Page<TeacherDetails> page, String keyword);
}
