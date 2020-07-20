package com.bryce;

import com.bryce.constant.Gender;
import com.bryce.entity.User;
import com.bryce.mapper.UserMapper;
import com.bryce.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DrumApplicationTests {

    @Resource
    UserMapper userMapper;

    @Resource
    UserService userService;

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
        User user = userMapper.selectById(5L);
        user.setUsername("fff");
        userMapper.updateById(user);

    }

}
