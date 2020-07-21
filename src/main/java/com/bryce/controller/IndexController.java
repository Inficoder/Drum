package com.bryce.controller;

import com.bryce.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName IndexController
 * @Description index
 * @Author Bryce
 * @Date 2020-07-20 09:55
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "hello,drum!!";
    }
    @RequestMapping("/pass")
    public String indexPass(){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("cccc", "aaa");
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            //subject.isPermitted();
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return "没有权限";
        }
        return "login success";
    }

    @RequestMapping("/test")
    public String test(@Valid @RequestBody User user){
        System.out.println(user);
        return "test!!";
    }
}
