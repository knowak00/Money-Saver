package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailSenderImpl implements EmailSender {
    private final JavaMailSender javaMailSender;

    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmails(String discount) {

        try {
            sendEmail("adrian.kowalski38@onet.pl","Dzisiejsze Promocje",discount);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    @RabbitListener
    public void sendEmail(String to,String title,String content) throws MessagingException {
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
       mimeMessageHelper.setTo(to);
       mimeMessageHelper.setSubject(title);
       mimeMessageHelper.setText(content);
       javaMailSender.send(mimeMessage);
    }
}
