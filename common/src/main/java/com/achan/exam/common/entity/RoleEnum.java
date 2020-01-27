package com.achan.exam.common.entity;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Achan
 * @date 2020/1/15
 */
@Getter
@ToString
public enum RoleEnum {

    /**
     * 学生
     */
    STUDENT(1, "student", "学生"),
    /**
     * 教师
     */
    TEACHER(2, "teacher", "教师"),
    /**
     * 教师组组长
     */
    TEACHER_PRINCIPAL(3, "principal", "教师组组长"),
    /**
     * 系统管理员
     */
    ADMIN(4, "admin", "管理员");

    private Integer id;
    private String name;
    private String nameZh;

    RoleEnum(Integer id, String name, String nameZh) {
        this.id = id;
        this.name = name;
        this.nameZh = nameZh;
    }
}
