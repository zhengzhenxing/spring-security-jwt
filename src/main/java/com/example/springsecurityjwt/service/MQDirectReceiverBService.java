package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.utils.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = QueueNames.DIRECT_QUEUE)
public class MQDirectReceiverBService {

    @RabbitHandler
    public void QueueReceiver(String message) {
        log.info("Receiver B: [{}]", message);
    }

}
