package com.achan.exam.commons.service;

import com.achan.exam.common.dto.ChapterDTO;
import com.achan.exam.common.service.impl.ChapterServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Achan
 * @date 2020/3/31
 */
@SpringBootTest
public class ChapterTest {

    @Autowired
    private ChapterServiceImpl chapterService;

    @Test
    public void listChaptersTest() {
        chapterService.listAllChapters(new QueryWrapper<ChapterDTO>()
                .orderByDesc("question_count"))
                .forEach(System.out::println);
    }
}
