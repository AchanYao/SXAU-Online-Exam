package com.achan.exam.common.vo.enumerate;

import lombok.Getter;

/**
 * @author Achan
 * @date 2020/1/29
 */
@Getter
public enum QuestionTypeEnum {
    // TODO 将类型改成启动任务，系统启动时从数据库中读取现有问题类型

    /**
     * 单选题
     */
    MULTIPLE_CHOICE(1, "multiple choice", "单选题"),
    /**
     * 填空题
     */
    Fill_BLANK_QUESTION(2, "fill blank question", "填空题"),
    /**
     * 判断题
     */
    TRUE_OR_FALSE(3, "true or false", "判断题"),
    /**
     * 主观题
     */
    SUBJECTIVE_QUESTION(4, "subjective question", "主观题"),
    /**
     * 编程题
     */
    PROGRAM_QUESTION(5, "program question", "编程题")
    ;

    private Integer id;
    private String name;
    private String nameZh;

    QuestionTypeEnum(int id, String name, String nameZh) {
        this.id = id;
        this.name = name;
        this.nameZh = nameZh;
    }
}
