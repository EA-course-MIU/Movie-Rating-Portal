package com.edu.service.impl;

import com.edu.dto.MediaRatingDto;
import com.edu.service.RabbitMQConsumer;
import com.edu.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
