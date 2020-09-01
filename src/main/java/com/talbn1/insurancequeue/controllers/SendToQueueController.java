package com.talbn1.insurancequeue.controllers;

import com.talbn1.insurancequeue.models.InsuranceReport;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author talbn on 8/27/2020
 **/

@RestController
@RequestMapping("/api")
public class SendToQueueController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/add")
    public String addToRabbit(@RequestBody InsuranceReport report){

        System.out.println(report.toString());

        try {
            rabbitTemplate.convertAndSend("Queue-1", report.toString());
        }catch (AmqpException amqpException){
            amqpException.printStackTrace();
        }

        return "Success";
    }
}
