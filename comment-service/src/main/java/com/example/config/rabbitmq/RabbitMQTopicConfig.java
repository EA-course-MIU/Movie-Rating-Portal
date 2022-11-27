//package com.example.config.rabbitmq;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQTopicConfig {
//
//    @Bean
//    TopicExchange topicExchange() {
//        return new TopicExchange("topic-exchange");
//    }
//
//    @Bean
//    Binding commentMovieQueueBinding(Queue commentMovieQueue, TopicExchange topicExchange) {
//        return BindingBuilder
//                .bind(commentMovieQueue)
//                .to(topicExchange)
//                .with("comment-movie-queue");
//    }
//
//}
