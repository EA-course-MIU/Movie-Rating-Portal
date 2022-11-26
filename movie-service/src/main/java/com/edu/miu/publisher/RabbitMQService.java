package com.edu.miu.publisher;

import com.edu.miu.dto.MediaDto;

public interface RabbitMQService {

    void sendExchange(String exchange, String routingKey, MediaDto mediaDto);

}
