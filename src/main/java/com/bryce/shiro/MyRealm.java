package com.bryce.shiro;


import com.bryce.entity.Permission;
import com.bryce.entity.Role;
import com.bryce.entity.User;
import com.bryce.mapper.UserMapper;
import com.bryce.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.security.Permissions;
import java.util.List;

/**
 * @ClassName Realm
 * @Description shiro realm
 * @Author Bryce
 * @Date 2020-07-20 10:03
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    @Bean(name = "userRealm")
    public MyRealm userRealm(){
        return new MyRealm();
    }

    /**
     * @Description //TODO 授权
     * @Author Bryce
     * @Date 10:13 2020/7/22
     * @Param [principalCollection]
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("=>授权doGetAuthorizationInfo");
        String username = (String) principalCollection.getPrimaryPrincipal();
        Long id = userService.getUserByUsername(username).getId();
        List<Role> roles = userService.listRolesById(id);
        List<Permission> permissions = userService.listPermissionsById(id);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加角色
        for (Role r : roles) {
            simpleAuthorizationInfo.addRole(r.getRole());
        }
        //添加权限
        for (Permission p : permissions) {
            simpleAuthorizationInfo.addStringPermission(p.getPermission());
        }
        System.out.println(simpleAuthorizationInfo.getStringPermissions());
        System.out.println(simpleAuthorizationInfo.getRoles());
        return simpleAuthorizationInfo;
    }

    /***
     * @Description //TODO 认证
     * @Author Bryce
     * @Date 10:11 2020/7/20
     * @Param [authenticationToken]
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("=>认证doAuthenticationToken");
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = userService.getUserByUsername(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
