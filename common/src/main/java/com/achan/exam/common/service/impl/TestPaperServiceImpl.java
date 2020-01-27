package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.TestPaper;
import com.achan.exam.common.mapper.TestPaperMapper;
import com.achan.exam.common.service.ITestPaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class TestPaperServiceImpl extends ServiceImpl<TestPaperMapper, TestPaper> implements ITestPaperService {

}
