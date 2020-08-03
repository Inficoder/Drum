package com.bryce;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bryce.constant.Gender;
import com.bryce.entity.Permission;
import com.bryce.entity.Role;
import com.bryce.entity.User;
import com.bryce.entity.UsersRoles;
import com.bryce.mapper.RoleMapper;
import com.bryce.mapper.UserMapper;
import com.bryce.mapper.UsersRolesMapper;
import com.bryce.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DrumApplicationTests {
    @Resource
    RoleMapper roleMapper;
    @Resource
    UserMapper userMapper;

    @Resource
    UserService userService;

    @Resource
    UsersRolesMapper usersRolesMapper;

    @Test
    void test1() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
    @Test
    void test2() {
        User byId = userService.getById(1L);
        System.out.println(byId);
    }
    @Test
    public void test3(){
        userMapper.insert(new User().setUsername("test").setPassword("test").setGender(Gender.MALE));
    }
    @Test
    public void test4(){
        User user = new User();
        user.setUsername("ccdc").setPassword("ccc").setGender(Gender.MALE).setId(3L);
        userMapper.updateById(user);
    }
    @Test
    public void test5(){
        User user = new User();
        user.setId(6L);
        user.setUsername("xxxd");
        userMapper.updateById(user);

    }

    @Test
    public void test6(){
        QueryWrapper<User> normal = new QueryWrapper<>();
        normal.groupBy("salt");
        List<Object> objects = userMapper.selectObjs(normal);
        objects.forEach(System.out::println);
    }

}
