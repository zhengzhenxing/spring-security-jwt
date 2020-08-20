package com.example.springsecurityjwt.config;

import com.example.springsecurityjwt.utils.QueueNames;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitmqConfig {

    @Bean
    public Queue queue() {
        return new Queue(QueueNames.DIRECT_QUEUE);
    }

    @Bean
    public Queue topicQueueA() {
        return new Queue(QueueNames.TOPIC_QUEUE_A);
    }

    @Bean
    public Queue topicQueueB() {
        return new Queue(QueueNames.TOPIC_QUEUE_B);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(QueueNames.TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeTopicMessageA(Queue topicQueueA, TopicExchange exchange) {
        return BindingBuilder.bind(topicQueueA).to(exchange).with(QueueNames.TOPIC_QUEUE_A);
    }

    @Bean
    Binding bindingExchangeTopicMessageB(Queue topicQueueB, TopicExchange exchange) {
        return BindingBuilder.bind(topicQueueB).to(exchange).with(QueueNames.TOPIC_QUEUE_B);
    }

    @Bean
    public Queue fanoutQueueA() {
        return new Queue(QueueNames.FANOUT_QUEUE_A);
    }

    @Bean
    public Queue fanoutQueueB() {
        return new Queue(QueueNames.FANOUT_QUEUE_B);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(QueueNames.FANOUT_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeFanoutMessageA(Queue fanoutQueueA, FanoutExchange exchange) {
        return BindingBuilder.bind(fanoutQueueA).to(exchange);
    }

    @Bean
    Binding bindingExchangeFanoutMessageB(Queue fanoutQueueB, FanoutExchange exchange) {
        return BindingBuilder.bind(fanoutQueueB).to(exchange);
    }

}
