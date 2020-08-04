package com.bryce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bryce.common.CommonResult;
import com.bryce.entity.*;
import com.bryce.mapper.*;
import com.bryce.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author Bryce
 * @Date 2020-07-20 13:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UsersRolesMapper usersRolesMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    PermissionMapper permissionMapper;
    @Resource
    RolesPermissionsMapper rolesPermissionsMapper;
    @Resource
    UserMapper userMapper;

    /**
     * @return java.util.List<com.bryce.entity.Role>
     * @Description //TODO 根据用户id查询所有的角色信息
     * @Author Bryce
     * @Date 17:10 2020/7/21
     * @Param [id]
     **/
    @Override
    public List<Role> listRolesById(Long id) {
        QueryWrapper<UsersRoles> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("role_id").eq("user_id", id);
        List<UsersRoles> roleList = usersRolesMapper.selectList(queryWrapper);
        List<Long> roleIdList = new ArrayList<>(roleList.size());
        for (UsersRoles ur : roleList) {
            roleIdList.add(ur.getRoleId());
        }
        List<Role> roles = roleMapper.selectBatchIds(roleIdList);
        return roles;
    }

    /***
     * @Description //TODO 根据用户的id查询所有权限信息
     * @Author Bryce
     * @Date 17:09 2020/7/21
     * @Param [id]
     * @return java.util.List<com.bryce.entity.Permission>
     **/
    @Override
    public List<Permission> listPermissionsById(Long id) {
        List<Role> roles = listRolesById(id);
        //role_ids
        Set<Long> permissionSet = new HashSet<>();
        for (Role r : roles) {
            QueryWrapper<RolesPermissions> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id", r.getId());
            List<RolesPermissions> rolesPermissionsList = rolesPermissionsMapper.selectList(queryWrapper);
            for (RolesPermissions s : rolesPermissionsList) {
                permissionSet.add(s.getPermissionId());
            }
        }
        List<Permission> rolesPermissions = permissionMapper.selectBatchIds(permissionSet);
        return rolesPermissions;
    }

    /**
     * @return com.bryce.entity.User
     * @Description //TODO 根据username查找user
     * @Author Bryce
     * @Date 10:12 2020/7/22
     * @Param [username]
     **/
    @Override
    public User getUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    /**
     * @return com.bryce.common.CommonResult
     * @Description //TODO 登录
     * @Author Bryce
     * @Date 10:13 2020/7/22
     * @Param [username, password]
     **/
    @Override
    public CommonResult login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        subject.login(usernamePasswordToken);
        return CommonResult.success("登陆成功");
    }

    @Override
    public CommonResult logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            return CommonResult.success("登出");
        }
        return CommonResult.failed("未登录");
    }
}