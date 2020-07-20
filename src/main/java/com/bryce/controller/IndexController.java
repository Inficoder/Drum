package com.bryce.controller;

import com.bryce.entity.User;
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

    @RequestMapping("/test")
    public String test(@Valid @RequestBody User user){
        System.out.println(user);
        return "test!!";
    }
}
