package com.achan.exam.common.task;

import java.util.List;

/**
 * @author Achan
 * @date 2020/1/17
 */
public interface ImportDataTask<T extends Object> {

    /**
     * 导入数据并抽象为Java对象
     * @return 读取的指定类型的所有对象
     */
    List<T> importData();
}
