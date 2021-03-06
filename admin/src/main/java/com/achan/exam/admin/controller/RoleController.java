package com.achan.exam.admin.controller;

import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.entity.Role;
import com.achan.exam.common.vo.enumerate.RoleEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Achan
 * @date 2020/1/19
 */
@RestController
@BaseResponse
@Api("用户角色管理")
@RequestMapping("/roles")
public class RoleController {

    @GetMapping("/all")
    @ApiOperation("获取所有用户角色")
    public List<Role> roleEnums() {
        RoleEnum[] roles = RoleEnum.values();
        List<Role> result = new ArrayList<>(roles.length);
        for (RoleEnum role : roles) {
            result.add(new Role().setName(role.getName()).setId(role.getId()).setNameZh(role.getNameZh()));
        }
        return result;
    }
}
