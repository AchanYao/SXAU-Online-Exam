package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.UserRole;
import com.achan.exam.common.mapper.UserRoleMapper;
import com.achan.exam.common.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
