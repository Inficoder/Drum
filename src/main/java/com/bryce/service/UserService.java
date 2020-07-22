package com.bryce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bryce.common.CommonResult;
import com.bryce.entity.Permission;
import com.bryce.entity.Role;
import com.bryce.entity.User;

import java.util.List;

/**
 * @ClassName UserService
 * @Description
 * @Author Bryce
 * @Date 2020-07-20 13:19
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户id查询所有的角色信息
     */

    List<Role> listRolesById(Long id);
    /**
     * 据用户的id查询所有权限信息
     */

    List<Permission> listPermissionsById(Long id);
    /**
     * 根据用户username查找id
     */
    User getUserByUsername(String username);

    /**
     * 登录
     */
    CommonResult login(String username, String password);
    /**
     * 登出
     */
    CommonResult logout();
}
