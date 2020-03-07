package com.achan.exam.common.mapper;

import com.achan.exam.common.entity.Role;
import com.achan.exam.common.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户角色
     * @param id 用户id
     * @return 用户角色集合
     */
    @Select("SELECT role.id, `name`, name_zh FROM user_role RIGHT JOIN role ON user_role.id = role.id WHERE user_id = #{id}")
    List<Role> loadRolesByUserId(Integer id);
}
