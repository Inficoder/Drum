package com.bryce.controller;

import com.bryce.common.CommonResult;
import com.bryce.entity.User;
import com.bryce.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName IndexController
 * @Description index
 * @Author Bryce
 * @Date 2020-07-20 09:55
 */
@RestController
public class IndexController {

    @Resource
    UserService userService;

    @RequestMapping("/")
    public String index(){
        return "hello,drum!!";
    }

    @RequestMapping("/test")
    public String test(@Valid @RequestBody User user){
        System.out.println(user);
        return "test!!";
    }
}
