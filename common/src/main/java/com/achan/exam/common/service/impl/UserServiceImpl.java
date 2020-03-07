package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.Role;
import com.achan.exam.common.entity.User;
import com.achan.exam.common.mapper.UserMapper;
import com.achan.exam.common.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        user.setRoles(loadUserRoles(user.getId()));
        return user;
    }

    public List<Role> loadUserRoles(Integer userId) {
        return this.baseMapper.loadRolesByUserId(userId);
    }
}
