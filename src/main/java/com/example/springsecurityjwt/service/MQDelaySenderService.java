package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.utils.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class MQDelaySenderService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String msg = "delay message";
        log.info("Sender: [{}]", msg);
        rabbitTemplate.convertAndSend(QueueNames.DELAYED_EXCHANGE, QueueNames.DELAYED_QUEUE, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", 5000);
                return message;
            }
        });
    }

}
