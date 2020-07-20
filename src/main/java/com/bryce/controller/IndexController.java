package com.bryce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public String test(){
        return "test!!";
    }
}
