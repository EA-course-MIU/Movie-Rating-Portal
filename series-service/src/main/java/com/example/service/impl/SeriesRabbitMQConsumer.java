package com.example.service.impl;

import com.example.dto.MediaRatingDto;
import com.example.service.RabbitMQConsumer;
import com.example.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeriesRabbitMQConsumer implements RabbitMQConsumer {

    private final SeriesService seriesService;
    @Override
    @RabbitListener(queues = {"rating-tv-series-queue"})
    public void receiveMessage(MediaRatingDto mediaRatingDto) {
        seriesService.updateRating(mediaRatingDto.getMediaId(), mediaRatingDto.getAverageRating());
    }
}
