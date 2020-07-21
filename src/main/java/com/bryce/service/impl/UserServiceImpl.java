package com.bryce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bryce.entity.*;
import com.bryce.mapper.*;
import com.bryce.service.UserService;
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
    
    /**
     * @Description //根据用户id查询所有的角色信息
     * @Author Bryce
     * @Date 17:10 2020/7/21
     * @Param [id]
     * @return java.util.List<com.bryce.entity.Role>
     **/
    @Override
    public List<Role> listRolesById(Long id) {
        QueryWrapper<UsersRoles> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("role_id").eq("user_id",id);
        List<UsersRoles> roleList = usersRolesMapper.selectList(queryWrapper);
        List<Long> roleIdList = new ArrayList<>(roleList.size());
        for(UsersRoles ur : roleList){
            roleIdList.add(ur.getRoleId());
        }
        roleIdList.forEach(System.out::println);
        List<Role> roles = roleMapper.selectBatchIds(roleIdList);
        return roles;
    }
    
    /***
     * @Description //根据用户的id查询所有权限信息
     * @Author Bryce
     * @Date 17:09 2020/7/21
     * @Param [id]
     * @return java.util.List<com.bryce.entity.Permission>
     **/
    @Override
    public List<Permission> listPermissionsById(Long id)
    {
        List<Role> roles = listRolesById(id);
        //role_ids
        Set<Long> permissionSet = new HashSet<>();
        for(Role r : roles){
            QueryWrapper<RolesPermissions> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id", r.getId());
            List<RolesPermissions> rolesPermissionsList = rolesPermissionsMapper.selectList(queryWrapper);
            rolesPermissionsList.forEach(System.out::println);

            for(RolesPermissions s : rolesPermissionsList){
                permissionSet.add(s.getPermissionId());
            }
        }
        permissionSet.forEach(System.out::println);
        List<Permission> rolesPermissions = permissionMapper.selectBatchIds(permissionSet);
        return rolesPermissions;
    }
}
