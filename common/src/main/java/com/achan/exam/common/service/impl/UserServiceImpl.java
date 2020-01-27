package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.User;
import com.achan.exam.common.mapper.UserMapper;
import com.achan.exam.common.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
