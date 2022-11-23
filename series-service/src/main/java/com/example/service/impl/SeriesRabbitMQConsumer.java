package com.example.service.impl;

import com.example.dto.MediaRatingDto;
import com.example.service.RabbitMQConsumer;
import com.example.service.SeriesService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class SeriesRabbitMQConsumer implements RabbitMQConsumer {

    @Autowired
    private SeriesService seriesService;
    @Override
    @RabbitListener(queues = {"rating-tv-series-queue"})
    public void receiveMessage(MediaRatingDto mediaRatingDto) {
        seriesService.updateRating(mediaRatingDto.getMediaId(), mediaRatingDto.getAverageRating());
    }
}
