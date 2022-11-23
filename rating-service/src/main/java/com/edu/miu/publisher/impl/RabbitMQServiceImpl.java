package com.edu.miu.publisher.impl;

import com.edu.miu.dto.MediaRatingDto;
import com.edu.miu.publisher.RabbitMQService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQServiceImpl implements RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendExchange(String exchange, String routingKey, MediaRatingDto messageData) {
        String message = String.format("Exchange: %s - Routing key: %s - Message: %s", exchange, routingKey, messageData);
        System.out.println(message);
        rabbitTemplate.convertAndSend(exchange, routingKey, messageData);
    }
}
