package com.bryce;

import com.bryce.entity.Mail;
import com.bryce.util.EmailUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
class DrumApplicationTests {

    @Resource
    EmailUtil emailUtil;

    @Test
    @SneakyThrows
    public void test6() {
        //private final String from = "767174855@qq.com";

        String to = "tianhaonan@buaa.edu.cn";
        Mail mail = new Mail();
        mail.setTo(to);
        mail.setContent("hello mail miao!");
        mail.setMsgId("1");
        mail.setTitle("hello qq m");
        emailUtil.sentDocumentMail(mail);
    }

}
