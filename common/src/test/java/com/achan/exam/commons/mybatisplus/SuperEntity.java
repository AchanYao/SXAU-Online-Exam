package com.achan.exam.commons.mybatisplus;

import lombok.Data;

import java.util.Date;

/**
 * @author Achan
 * @since 2020/1/13 18:34
 */
@Data
public abstract class SuperEntity {

    protected int id;
    protected Date createTime;
    protected Date modifyTime;
}
