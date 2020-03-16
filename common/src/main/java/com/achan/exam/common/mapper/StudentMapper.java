package com.achan.exam.common.mapper;

import com.achan.exam.common.dto.student.StudentDetails;
import com.achan.exam.common.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 学生表 Mapper 接口
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 分页获取学生详细信息
     * @param page 分页
     * @return
     */
    @Select("select student.id, student.create_time, student.modify_time, student.number, student.`name`, student.id_number, student.phone, student.clazz_id,  student.email, student.user_id, clazz.`name` as clazz_name, user.enable from student left join clazz on student.clazz_id = clazz.id left join user on student.user_id = user.id")
    Page<StudentDetails> getStudentWithDetails(Page<StudentDetails> page);

    /**
     * 关键字分页查询
     * @param keyword 关键字
     * @param page 分页
     * @return
     */
    @Select("SELECT student.id, student.create_time, student.modify_time, student.number, student.`name`, student.id_number, student.phone, student.clazz_id,  student.email, student.user_id, clazz.`name` as clazz_name, user.enable from student left join clazz on student.clazz_id = clazz.id left join user on student.user_id = user.id where student.`name` like '%${keyword}%' or student.number like '%${keyword}%' or clazz.`name` like '%${keyword}%'")
    Page<StudentDetails> getStudentWithDetailsByKeyword(Page<StudentDetails> page, String keyword);
}
