package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqListener {
    private final EmailSender emailSender;

    public RabbitMqListener(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @RabbitListener(queues = "Discounts")
 public void handleDiscounts(String discount){
   emailSender.sendEmails(discount);
    }
}
