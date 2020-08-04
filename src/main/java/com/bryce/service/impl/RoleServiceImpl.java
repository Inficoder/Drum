package com.bryce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bryce.entity.Role;
import com.bryce.mapper.RoleMapper;
import com.bryce.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bryce
 * @since 2020-07-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
