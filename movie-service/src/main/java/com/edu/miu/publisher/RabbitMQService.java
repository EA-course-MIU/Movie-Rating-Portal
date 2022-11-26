package com.edu.miu.publisher;

import com.edu.miu.dto.RatingDto;

public interface RabbitMQService {

    void sendExchange(String exchange, String routingKey, RatingDto messageData);

}
