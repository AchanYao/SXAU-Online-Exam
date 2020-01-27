package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.Role;
import com.achan.exam.common.mapper.RoleMapper;
import com.achan.exam.common.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
