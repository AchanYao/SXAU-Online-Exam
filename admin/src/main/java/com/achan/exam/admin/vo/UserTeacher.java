package com.achan.exam.admin.vo;

import com.achan.exam.common.entity.Teacher;
import com.achan.exam.common.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Achan
 * @date 2020/1/19
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "教师用户封装")
public class UserTeacher {

    private Teacher teacher;
    private User user;
}
