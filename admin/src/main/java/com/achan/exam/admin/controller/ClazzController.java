package com.achan.exam.admin.controller;

import com.achan.exam.admin.task.ImportClazzTask;
import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.entity.Clazz;
import com.achan.exam.common.service.impl.ClazzServiceImpl;
import com.achan.exam.util.file.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Achan
 * @date 2020/1/17
 */
@RestController
@RequestMapping("/api/classes")
@Api("班级操作")
@BaseResponse
public class ClazzController {

    @Autowired
    private ClazzServiceImpl clazzService;

    @GetMapping("/all")
    @ApiOperation("所有班级")
    public List<Clazz> all() {
        return clazzService.list();
    }

    @GetMapping("/page")
    @ApiOperation("分页获取班级")
    public Page<Clazz> page(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return clazzService.page(new Page<>(page, size));
        } else {
            return clazzService.page(new Page<>(page, size), new QueryWrapper<Clazz>().lambda().like(Clazz::getName, keyword));
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("获取指定id班级")
    public Clazz getClazz(@PathVariable int id) {
        return clazzService.getById(id);
    }

    @PostMapping("/add")
    @ApiOperation("新增班级")
    public boolean addClazz(@RequestBody Clazz clazz) {
        return clazzService.save(clazz);
    }

    @PutMapping("/update")
    @ApiOperation("修改班级")
    public boolean updateClazz(@RequestBody Clazz clazz) {
        return clazzService.updateById(clazz);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除班级")
    public boolean deleteClazz(@PathVariable int id) {
        return clazzService.removeById(id);
    }

    @PostMapping("/import")
    @ApiOperation("班级信息导入")
    public List<Clazz> importClazz(MultipartFile file) throws IOException {
        File newFile = FileUtil.transferTo(file, new File("data/csv").getAbsoluteFile());
        Path filePath = newFile.toPath();
        // try-with-resources
        var data = Files.lines(filePath, Charset.forName("GBK"));
        return new ImportClazzTask().setData(data).importData();
    }

    @GetMapping("/search")
    @ApiOperation("查找班级")
    public List<Clazz> searchClazz(@RequestParam String keyword) {
        return clazzService.list(new QueryWrapper<Clazz>().lambda().like(Clazz::getName, keyword));
    }

    @GetMapping("/count")
    @ApiOperation("班级数量")
    public int count() {
        return clazzService.count();
    }
}
