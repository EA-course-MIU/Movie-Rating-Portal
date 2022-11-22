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
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding ratingMovieQueueBinding(Queue ratingMovieQueue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(ratingMovieQueue)
                .to(topicExchange)
                .with("rating-movie-queue");
    }

    @Bean
    Binding ratingTvSeriesQueueBinding(Queue ratingMovieQueue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(ratingMovieQueue)
                .to(topicExchange)
                .with("rating-tv-series-queue");
    }
}
