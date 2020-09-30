package com.bryce.util;

import com.bryce.entity.Mail;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @ClassName EmailUtil
 * @Description
 * @Author Bryce
 * @Date 2020-09-23 14:24
 */
@Slf4j
@Component
public class EmailUtil {
    /**
     * 发件人
     */
    //@Value("${spring.mail.username}")
    private String from = "767174855@qq.com";

    @Resource
    private JavaMailSender javaMailSender;

    @Async("piceaTaskExecutor")
    @SneakyThrows
    public void sentDocumentMail(Mail mail){

        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();
        // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.host", "smtp.qq.com");
        // 需要请求认证
        props.setProperty("mail.smtp.auth", "true");

        props.setProperty("mail.transport.protocol", "smtps");

        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");


        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);

        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);
        String [] fileArray={"root/services/logs/web_error.log"};

        MimeMessage message = javaMailSender.createMimeMessage();
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        transport.connect("767174855@qq.com", "mpkjpgzmhjfubgah");

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom(from);
        helper.setTo(mail.getTo());
        helper.setSubject("test");
        helper.setText("test document");
        FileSystemResource file = null;
        file = new FileSystemResource(fileArray[0]);
        String filename = "email.txt";
        helper.addAttachment(filename, file);
        // 7. 关闭连接
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

}

