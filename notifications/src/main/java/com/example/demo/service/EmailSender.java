package com.example.demo.service;

import org.springframework.messaging.MessagingException;

public interface EmailSender {
    void sendEmails(String discounts);
    public void sendEmail(String to,String title,String content) throws MessagingException, javax.mail.MessagingException;
}
