package com.bryce.controller;

import com.bryce.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description user controller
 * @Author Bryce
 * @Date 2020-07-20 10:25
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user.getUsername()+"----"+user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        //String saltPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        //System.out.println(saltPassword);
        subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
        return "login";
    }
    @RequestMapping("/loginPage")
    public String loginPage(){
        return "please login";
    }
    @RequestMapping("/unauthorizedPage")
    public String unauthorizedPage(){
        return "unauthorizedPage";
    }
}
