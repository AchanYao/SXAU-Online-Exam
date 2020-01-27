package com.achan.exam.admin.vo;

import com.achan.exam.common.entity.Student;
import com.achan.exam.common.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author Achan
 * @date 2020/1/19
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "学生用户封装")
public class UserStudent {

    @NotNull
    private User user;
    @NotNull
    private Student student;
}
