package com.edu.service;

import com.edu.dto.MediaRatingDto;

public interface RabbitMQConsumer {
    void receiveMessage(MediaRatingDto mediaRatingDto);
}
