package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.Chapter;
import com.achan.exam.common.mapper.ChapterMapper;
import com.achan.exam.common.service.IChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
