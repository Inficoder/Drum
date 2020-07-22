package com.bryce.controller;

import com.bryce.common.CommonResult;
import com.bryce.entity.User;
import com.bryce.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Enumeration;

/**
 * @ClassName IndexController
 * @Description index
 * @Author Bryce
 * @Date 2020-07-20 09:55
 */
@RestController
public class IndexController {

    @RequiresAuthentication
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
