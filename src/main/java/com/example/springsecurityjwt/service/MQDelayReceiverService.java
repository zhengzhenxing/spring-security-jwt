package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.utils.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQDelayReceiverService {

    @RabbitListener(queues = QueueNames.DELAYED_QUEUE)
    public void receive(String msg) {
        log.info("Receiver: [{}]", msg);
    }

}
