package com.edu.miu.publisher.impl;

import com.edu.miu.dto.MediaDto;
import com.edu.miu.publisher.RabbitMQService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQServiceImpl implements RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendExchange(String exchange, String routingKey, MediaDto mediaDto) {
        String message = String.format("Exchange: %s - Routing key: %s - Message: %s", exchange, routingKey, mediaDto);
        System.out.println(message);
        rabbitTemplate.convertAndSend(exchange, routingKey, mediaDto);
    }
}
