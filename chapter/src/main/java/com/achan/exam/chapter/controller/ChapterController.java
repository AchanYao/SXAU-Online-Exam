package com.achan.exam.chapter.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.dto.ChapterDTO;
import com.achan.exam.common.entity.Chapter;
import com.achan.exam.common.service.impl.ChapterServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Achan
 * @date 2020/3/27
 */
@RestController
@BaseResponse
@Api("章节Controller")
@RequestMapping("/api/chapters")
public class ChapterController {

    @Autowired
    private ChapterServiceImpl chapterService;

    @GetMapping("all")
    public List<ChapterDTO> allChapters() {
        return chapterService.listAllChapters(new QueryWrapper<>().orderByDesc("question_count"));
    }

    @PostMapping("add")
    public Chapter addChapter(@RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return chapter;
    }

    @PutMapping("update")
    public Chapter updateChapter(@RequestBody Chapter chapter) {
        chapterService.updateById(chapter);
        return chapter;
    }

    @DeleteMapping("delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return chapterService.removeById(id);
    }
}
