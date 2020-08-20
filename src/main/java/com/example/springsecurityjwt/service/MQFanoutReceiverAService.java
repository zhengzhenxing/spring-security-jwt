package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.utils.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RabbitListener(queues = QueueNames.FANOUT_QUEUE_A)
public class MQFanoutReceiverAService {

    @RabbitHandler
    public void process(String message) {
        log.info("fanout Receiver A: [{}]", message);
    }

}
