package com.example.service;

import com.example.dto.MediaRatingDto;

public interface RabbitMQConsumer {
    void receiveMessage(MediaRatingDto mediaRatingDto);
}
