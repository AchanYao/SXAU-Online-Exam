package com.achan.exam.admin.task;

import com.achan.exam.common.entity.*;
import com.achan.exam.common.service.impl.ClazzServiceImpl;
import com.achan.exam.common.task.ImportDataTask;
import com.achan.exam.common.util.ApplicationContextUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Achan
 * @date 2020/1/17
 */
@Data
@Slf4j
@Component
@Accessors(chain = true)
public class ImportClazzTask extends QuartzJobBean implements ImportDataTask<Clazz> {

    @Autowired
    private ClazzServiceImpl clazzService;
    private Stream<String> data;

    @Override
    public List<Clazz> importData() {
        // 预防现有对象没有使用spring注入
        if (clazzService == null) {
            clazzService = ApplicationContextUtil.getBean(ClazzServiceImpl.class);
        }
        Supplier<Stream<String>> streamSupplier = () -> data;
        List<Clazz> result = new ArrayList<>();
        streamSupplier.get().skip(1).collect(Collectors.toList()).forEach(s -> {
            String[] line = s.split("\t");
            Clazz clazz = new Clazz().setId(Integer.parseInt(line[0])).setName(line[1]);
            result.add(clazz);
        });
        clazzService.saveBatch(result, result.size());
        return result;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        importData();
    }
}
