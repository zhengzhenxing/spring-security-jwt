package com.example.springsecurityjwt.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
@EnableRabbit
public class RabbitmqConfig {

    @Bean
    public Queue queue() {
        return new Queue("Queue1");
    }

}
