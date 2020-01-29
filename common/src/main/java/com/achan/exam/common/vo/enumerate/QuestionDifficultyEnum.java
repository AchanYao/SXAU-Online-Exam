package com.achan.exam.common.vo.enumerate;

import lombok.Getter;

/**
 * @author Achan
 * @date 2020/1/29
 */
@Getter
public enum QuestionDifficultyEnum {

    // TODO 将难度获取改成启动任务，系统启动时从数据库中读取现有问题难度

    /**
     * 容易
     */
    EASY(1, "easy", "容易"),
    /**
     * 中等
     */
    MEDIUM(2, "medium", "中等"),
    /**
     * 困难
     */
    DIFFICULT(3, "difficult", "困难"),
    ;

    private Integer id;
    private String name;
    private String nameZh;

    QuestionDifficultyEnum(int id, String name, String nameZh) {
        this.id = id;
        this.name = name;
        this.nameZh = nameZh;
    }

}
