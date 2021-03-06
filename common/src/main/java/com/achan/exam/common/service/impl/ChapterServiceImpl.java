package com.achan.exam.common.service.impl;

import com.achan.exam.common.dto.ChapterDTO;
import com.achan.exam.common.entity.Chapter;
import com.achan.exam.common.mapper.ChapterMapper;
import com.achan.exam.common.service.IChapterService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 章节 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements IChapterService {

    public List<ChapterDTO> listAllChapters(Wrapper<?> wrapper) {
        return this.baseMapper.listChapters(wrapper);
    }
}
