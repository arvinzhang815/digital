package com.yingwu.digital.service.impl;

import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/25
 **/
@Service/*("eMailService")*/
public class MailServiceImpl implements MailService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.fromMail.addr}")
    private String from;
    @Override
    public void sendSimpleMail(String to, String subject, String content){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("邮件已经发送给：" + to);
        } catch (Exception e) {
            log.error("发送给：" + to + "的邮件，发生异常：" + e);
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            log.info("HTML邮件已经发送给：" + to);
        } catch (Exception e) {
            log.error("发送给：" + to + "的HTML邮件，发生异常：" + e);
        }
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);//可以添加多个附件

            mailSender.send(message);
            log.info("带附件邮件已经发送给：" + to);
        } catch (Exception e) {
            log.error("发送给：" + to + "的带附件邮件，发生异常：" + e);
        }
    }
}
