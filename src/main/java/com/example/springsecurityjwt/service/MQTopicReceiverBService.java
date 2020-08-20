package com.example.springsecurityjwt.service;
import com.example.springsecurityjwt.utils.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RabbitListener(queues = QueueNames.TOPIC_QUEUE_B)
public class MQTopicReceiverBService {

    @RabbitHandler
    public void process(String msg) {
        log.info("Topic ReceiverA: [{}]", msg);
    }

}
