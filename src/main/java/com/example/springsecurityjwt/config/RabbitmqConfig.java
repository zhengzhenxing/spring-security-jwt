package com.example.springsecurityjwt.config;

import com.example.springsecurityjwt.utils.QueueNames;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableRabbit
public class RabbitmqConfig {

    @Bean
    public Queue queue() {
        return new Queue(QueueNames.DIRECT_QUEUE);
    }

    // 通配符消息

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

    // 广播消息

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

    // 延迟消息

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(QueueNames.DELAYED_EXCHANGE, "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue delayQueue() {
        return new Queue(QueueNames.DELAYED_QUEUE, true);
    }

    @Bean
    public Binding bindingExchangeDelayMessage(Queue delayQueue, CustomExchange exchange) {
        return BindingBuilder.bind(delayQueue).to(exchange).with(QueueNames.DELAYED_QUEUE).noargs();
    }

}
