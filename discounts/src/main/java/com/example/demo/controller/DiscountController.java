package com.example.demo.controller;

import com.example.demo.service.DiscountService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/discount")
public class DiscountController {
    private final DiscountService discountService;
    private final RabbitTemplate rabbitTemplate;
 @Autowired
    public DiscountController(DiscountService discountService, RabbitTemplate rabbitTemplate) {
        this.discountService = discountService;
     this.rabbitTemplate = rabbitTemplate;
 }
    @PostMapping
    void findDiscount(){
       String discounts = discountService.getDiscounts();
        rabbitTemplate.convertAndSend("Discounts",discounts);
    }
}
