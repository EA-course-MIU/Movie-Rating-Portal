package com.example.service;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloServiceImpl implements HelloService{
    private final RabbitTemplate rabbitTemplate;

    private final DirectExchange direct;

    private final Queue hiQueue1;
    private final Queue hiQueue2;

    @Override
    public void send() {
        rabbitTemplate.convertAndSend(hiQueue1.getName(), "Q1 -- Hello World: " + System.currentTimeMillis());
        rabbitTemplate.convertAndSend(hiQueue2.getName(), "Q2 -- Hello World: " + System.currentTimeMillis());
    }

    @Override
    public void directSend() {
        rabbitTemplate.convertAndSend(direct.getName(), "orange", "Hello");
    }
}
