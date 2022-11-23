package com.edu.miu.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopicConfig {

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("movie-topic-exchange");
    }

    @Bean
    Binding removeRatingQueueBinding(Queue removeRatingQueue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(removeRatingQueue)
                .to(topicExchange)
                .with("remove-rating-queue");
    }

}
