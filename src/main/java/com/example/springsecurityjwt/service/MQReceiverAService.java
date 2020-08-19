package com.example.springsecurityjwt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = "Queue1")
public class MQReceiverAService {

    @RabbitHandler
    public void QueueReceiver(String message) {
        log.info("Receiver A: [{}]", message);
    }

}
