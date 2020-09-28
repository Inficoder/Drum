package com.bryce.controller;

import com.bryce.entity.Mail;
import com.bryce.entity.User;
import com.bryce.util.EmailUtil;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Resource
    EmailUtil emailUtil;

    @RequestMapping("/")
    @Async
    @SneakyThrows
    public String index()
    {
        System.out.println("在控制层调用的线程名："+ Thread.currentThread().getName());
        String to = "tianhaonan@buaa.edu.cn";
        Mail mail = new Mail();
        mail.setTo(to);
        mail.setContent("hello mail miao!");
        mail.setMsgId("1");
        mail.setTitle("hello qq m");
        emailUtil.sentDocumentMail(mail);
        return "miao!";
    }

    @RequestMapping("/test")
    public String test(@Valid @RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        int maxInactiveInterval = session.getMaxInactiveInterval();
        System.out.println(maxInactiveInterval);

        System.out.println(user);
        return "test!!";
    }
}
