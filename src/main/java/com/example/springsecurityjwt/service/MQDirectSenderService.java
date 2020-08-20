package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.utils.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class MQDirectSenderService {

    @Resource
    private AmqpTemplate rabbitTemplate;

    public void send(String context) {
        log.info("Sender: [{}]", context);
        rabbitTemplate.convertAndSend(QueueNames.DIRECT_QUEUE, context);
    }

}
