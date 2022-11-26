package com.miu.service;

import com.miu.dto.MediaRatingDto;

public interface RabbitMQConsumer {
    void receiveMessage(MediaRatingDto mediaRatingDto);
}
