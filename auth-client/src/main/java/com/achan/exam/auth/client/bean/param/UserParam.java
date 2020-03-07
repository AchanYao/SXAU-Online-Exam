package com.achan.exam.auth.client.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Achan
 * @date 2020/3/7
 */
@Data
@ApiModel("用户参数")
public class UserParam {

    @ApiModelProperty("用户名")
    @NotNull
    @NotBlank
    private String username;
    @ApiModelProperty("密码")
    @NotNull
    @NotBlank
    private String password;
}
