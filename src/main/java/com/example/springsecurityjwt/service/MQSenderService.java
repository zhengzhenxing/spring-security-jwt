package com.example.springsecurityjwt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class MQSenderService {

    @Resource
    private AmqpTemplate rabbitTemplate;

    public void send(String context) {
        log.info("Sender: [{}]", context);
        rabbitTemplate.convertAndSend("Queue1", context);
    }

}
