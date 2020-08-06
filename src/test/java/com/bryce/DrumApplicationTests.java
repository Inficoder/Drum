package com.bryce;

import com.bryce.mapper.RoleMapper;
import com.bryce.mapper.UserMapper;
import com.bryce.mapper.UsersRolesMapper;
import com.bryce.service.UserService;
import com.bryce.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
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

//    @Resource
//    private RedisUtils redisUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void test6() {
        redisUtils.hset("map", "a", 1);
        redisUtils.hset("map", "b", 2);
        redisUtils.hset("map", "c", 3);
        Map<Object, Object> map = redisUtils.hmget("map");
        System.out.println(redisUtils.hasKey("map"));
    }

}
