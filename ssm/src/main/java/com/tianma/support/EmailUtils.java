package com.tianma.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;

/**
 * Created by zuowenxia on 2017/4/27.
 */
@Service
public class EmailUtils {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailUtils(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 无附件简单文本
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleEmail(String from, String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);//发送者.
        message.setTo(to);//接收者.
        message.setSubject(subject);//邮件主题.
        message.setText(content);//邮件内容.

        mailSender.send(message);//发送邮件
    }

    /**
     * 邮件内容为纯文本
     * @param from
     * @param to
     * @param subject: 主题
     * @param content: 内容
     * @param
     * @throws Exception
     */
    public void sendAttachmentsEmail(String from, String to, String subject, String content,
                                     Map<String, InputStreamResource> attachments) throws Exception {

        //javax.mail.internet.MimeMessage
        MimeMessage mimeMessage =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //基本设置
        helper.setFrom(from);//发送者.
        helper.setTo(to);//接收者.
        helper.setSubject(subject);//邮件主题.
        helper.setText(content);//邮件内容.

        //附件
        for(String key : attachments.keySet()) {
            InputStreamResource inputStreamResource = attachments.get(key);
            helper.addAttachment(key, inputStreamResource);
        }

        mailSender.send(mimeMessage);
    }

    /**
     * 邮件内容为网页格式
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param attachments
     * @throws Exception
     */
    public void sendInlineMail(String from, String to, String subject, String content,
                               Map<String, InputStreamResource> attachments) throws Exception {
        //javax.mail.internet.MimeMessage
        MimeMessage mimeMessage =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //基本设置
        helper.setFrom(from);//发送者.
        helper.setTo(to);//接收者.
        helper.setSubject(subject);//邮件主题.
        helper.setText("<body>这是图片：<img src='cid:head1' />这是图片：<img src='cid:head2' /></body>", true); //邮件内容, HTML.

        //附件
        for(String key : attachments.keySet()) {
            InputStreamResource inputStreamResource = attachments.get(key);
            helper.addAttachment(key, inputStreamResource);
        }

        mailSender.send(mimeMessage);
    }
}