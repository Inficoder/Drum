package com.bryce.controller;

import com.bryce.entity.User;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @RequiresRoles("admin")
    @RequestMapping("/test")
    public String test(@Valid @RequestBody User user ,HttpServletRequest request){
        HttpSession session = request.getSession();
        int maxInactiveInterval = session.getMaxInactiveInterval();
        System.out.println(maxInactiveInterval);

        System.out.println(user);
        return "test!!";
    }
}
