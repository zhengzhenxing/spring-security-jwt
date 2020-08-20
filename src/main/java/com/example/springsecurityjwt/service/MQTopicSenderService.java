package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.utils.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class MQTopicSenderService {

    @Resource
    private AmqpTemplate amqpTemplate;

    public void send() {
        String context = "topic";
        log.info("Sender: [{}]", context);
        amqpTemplate.convertAndSend(QueueNames.TOPIC_EXCHANGE, QueueNames.TOPIC_QUEUE_1, context);
    }

    public void sendA() {
        String context = "topic a";
        log.info("Sender: [{}]", context);
        amqpTemplate.convertAndSend(QueueNames.TOPIC_EXCHANGE, QueueNames.TOPIC_QUEUE_A, context);
    }

    public void sendB() {
        String context = "topic b";
        log.info("Sender: [{}]", context);
        amqpTemplate.convertAndSend(QueueNames.TOPIC_EXCHANGE, QueueNames.TOPIC_QUEUE_B, context);
    }

}
