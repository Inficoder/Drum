package com.bryce.util;

import com.bryce.entity.Mail;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

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


    /**
     * 发送简单邮件
     * @param mail
     */
    public boolean send(Mail mail) {
        JavaMailSender mailSender = new JavaMailSenderImpl();
        String to = mail.getTo();// 目标邮箱
        String title = mail.getTitle();// 邮件标题
        String content = mail.getContent();// 邮件正文

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        try {
            mailSender.send(message);
            log.info("邮件发送成功");
            return true;
        }catch(MailException e) {
            log.error("邮件发送失败, to: {}, title: {}", to, title, e);
            return false;
        }
    }
    public void sendEmail(Mail mail){
         //发邮件
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人邮件地址(上面获取到的，也可以直接填写,string类型)
        message.setFrom(from);
        //收件人邮件地址
        message.setTo(mail.getTo());
        //邮件主题
        message.setSubject("注册验证码二");
        //邮件正文
        message.setText("我是普通文本邮件内容");
        javaMailSender.send(message);
    }

     public void sentDocumentMail(Mail mail){
        String [] fileArray={"root/services/logs/web_error.log"};
         System.out.println("email thread ~~~~~~~~~~~~~~");
        new Thread(() -> {
            log.info("email thread :{}",Thread.currentThread().getId());
        MimeMessage message=javaMailSender.createMimeMessage();
            System.out.println(1);
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            System.out.println(2);
            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject("test");
            helper.setText("test document");
            FileSystemResource file = null;
            file = new FileSystemResource(fileArray[0]);
            String filename = "email.txt";
            helper.addAttachment(filename, file);
            System.out.println(3);
            //验证文件数据是否为空
//            if(null != fileArray){
//                FileSystemResource file=null;
//                for (int i = 0; i < fileArray.length; i++) {
//                    //添加附件
//                    file=new FileSystemResource(fileArray[i]);
//                    helper.addAttachment(fileArray[i].substring(fileArray[i].lastIndexOf(File.separator)), file);
//                }
//            }
            javaMailSender.send(message);
            System.out.println(4);
            System.out.println("带附件的邮件发送成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送带附件的邮件失败");
        }
        }).start();
    }


    public void sendSMTPSMail(Mail mail) {
        String [] fileArray={"root/services/logs/web_error.log"};
        // 发送邮件是耗时任务，需要另起线程，不影响主线程。此处可使用线程池处理
        new Thread(() -> {
            MimeMessage message = javaMailSender.createMimeMessage();

            try {
                MimeMessageHelper helper = new MimeMessageHelper(message,true);
                helper.setFrom(from);
                helper.setTo(mail.getTo());
                helper.setSubject("test");
                helper.setText("test document");
                FileSystemResource file = null;
                file = new FileSystemResource(fileArray[0]);
                String filename = "email.txt";
                helper.addAttachment(filename, file);
                javaMailSender.send(message);
                System.out.println("带附件的邮件发送成功");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("发送带附件的邮件失败");
            }
        }).start();
    }
}

