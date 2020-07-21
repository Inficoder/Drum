package com.bryce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bryce.entity.Permission;
import com.bryce.mapper.PermissionMapper;

import com.bryce.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bryce
 * @since 2020-07-21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
