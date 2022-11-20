package com.example.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("tut.direct");
    }

    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1a(DirectExchange direct,
                             Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1)
                .to(direct)
                .with("orange");
    }

    @Bean
    public Binding binding1b(DirectExchange direct,
                             Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1)
                .to(direct)
                .with("black");
    }

    @Bean
    public Binding binding2a(DirectExchange direct,
                             Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2)
                .to(direct)
                .with("green");
    }

    @Bean
    public Binding binding2b(DirectExchange direct,
                             Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2)
                .to(direct)
                .with("black");
    }

    @Bean
    public Queue hiQueue1(){
        return new Queue("hello-queue-1", true);
    }
    @Bean
    public Queue hiQueue2(){
        return new Queue("hello-queue-2", true);
    }
    @Bean
    FanoutExchange helloFanoutExchange(){
        return new FanoutExchange("hello-fanout-exchange");
    }

    @Bean
    public Binding helloQueue1Binding(Queue hiQueue1, FanoutExchange helloFanoutExchange){
        return BindingBuilder.bind(hiQueue1).to(helloFanoutExchange);
    }
    @Bean
    public Binding helloQueue2Binding(Queue hiQueue2, FanoutExchange helloFanoutExchange){
        return BindingBuilder.bind(hiQueue2).to(helloFanoutExchange);
    }
}
