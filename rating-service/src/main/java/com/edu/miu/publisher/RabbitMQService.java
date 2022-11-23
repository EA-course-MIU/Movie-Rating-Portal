package com.edu.miu.publisher;

import com.edu.miu.dto.MediaRatingDto;

public interface RabbitMQService {

    void sendExchange(String exchange, String routingKey, MediaRatingDto messageData);

}
