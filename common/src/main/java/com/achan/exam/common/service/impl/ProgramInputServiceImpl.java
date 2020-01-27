package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.ProgramInput;
import com.achan.exam.common.mapper.ProgramInputMapper;
import com.achan.exam.common.service.IProgramInputService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 编程题的输入输出 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class ProgramInputServiceImpl extends ServiceImpl<ProgramInputMapper, ProgramInput> implements IProgramInputService {

}
