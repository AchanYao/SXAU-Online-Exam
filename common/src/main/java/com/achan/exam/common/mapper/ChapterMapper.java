package com.achan.exam.common.mapper;

import com.achan.exam.common.dto.ChapterDTO;
import com.achan.exam.common.entity.Chapter;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 章节 Mapper 接口
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
public interface ChapterMapper extends BaseMapper<Chapter> {

    /**
     * 获取所有课程
     *
     * @param wrapper 条件构造器
     * @return all chapters
     */
    @Select("SELECT COUNT(1) AS question_count, chapter_id AS id, chapter.`name` AS NAME, chapter.description AS description FROM question LEFT JOIN chapter ON chapter.id = chapter_id ${ew.customSqlSegment}")
    List<ChapterDTO> listChapters(@Param(Constants.WRAPPER) Wrapper<?> wrapper);
}
