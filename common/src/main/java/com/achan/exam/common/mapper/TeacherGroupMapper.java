package com.achan.exam.common.mapper;

import com.achan.exam.common.entity.TeacherGroup;
import com.achan.exam.common.vo.group.TeacherGroupOverview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 教师组表 Mapper 接口
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
public interface TeacherGroupMapper extends BaseMapper<TeacherGroup> {

    /**
     * 获取教师组概览信息
     * @param id 所要查找的教师组id
     * @return 教师组概览
     */
    @Select("select " +
            "TG.id as id, " +
            "TG.create_time as createTime, " +
            "TG.modify_time as modifyTime, " +
            "TG.principal_id as principalId, " +
            "TG.course_id as courseId, " +
            "TG.number as number, " +
            "C.id as course_id, " +
            "C.name as course_name, " +
            "C.name_zh as course_nameZh, " +
            "T.id as principal_id, " +
            "T.name as principal_name " +
            "from teacher_group TG " +
            "left outer join teacher T on TG.principal_id = T.id " +
            "left outer join course C on TG.course_id = C.id " +
            "where TG.id = #{id}")
    TeacherGroupOverview details(Integer id);

    /**
     * 分页获取教师组概览信息
     * @param page 分页配置
     * @return 教师组概览
     */
    @Select("select " +
            "TG.id as id, " +
            "TG.create_time as createTime, " +
            "TG.modify_time as modifyTime, " +
            "TG.principal_id as principalId, " +
            "TG.course_id as courseId, " +
            "TG.number as number, " +
            "C.id as course_id, " +
            "C.name as course_name, " +
            "C.name_zh as course_nameZh, " +
            "T.id as principal_id, " +
            "T.name as principal_name " +
            "from teacher_group TG " +
            "left outer join teacher T on TG.principal_id = T.id " +
            "left outer join course C on TG.course_id = C.id")
    Page<TeacherGroupOverview> pageDetails(Page<?> page);

    /**
     * 分页获取教师组概览信息
     * @param page 分页配置
     * @param keyword 关键字
     * @return 教师组概览
     */
    @Select("select " +
            "TG.id as id, " +
            "TG.create_time as createTime, " +
            "TG.modify_time as modifyTime, " +
            "TG.principal_id as principalId, " +
            "TG.course_id as courseId, " +
            "TG.number as number, " +
            "C.id as course_id, " +
            "C.name as course_name, " +
            "C.name_zh as course_nameZh, " +
            "T.id as principal_id, " +
            "T.name as principal_name " +
            "from teacher_group TG " +
            "left outer join teacher T on TG.principal_id = T.id " +
            "left outer join course C on TG.course_id = C.id " +
            "where C.name like '%${keyword}%' " +
            "or C.name_zh like '%${keyword}%' " +
            "or TG.number like '%${keyword}%'")
    Page<TeacherGroupOverview> pageDetailsWithKeyword(Page<?> page, String keyword);
}
