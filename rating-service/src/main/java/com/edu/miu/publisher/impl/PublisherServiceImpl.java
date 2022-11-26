package com.edu.miu.publisher.impl;

import com.edu.miu.dto.MediaRatingDto;
import com.edu.miu.publisher.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendMessageToMovieService(MediaRatingDto mediaRatingDto) {
        kafkaTemplate.send("avg-rating-movie-topic", mediaRatingDto);
    }

    @Override
    public void sendMessageToTvSeriesService(MediaRatingDto mediaRatingDto) {
        kafkaTemplate.send("avg-rating-series-topic", mediaRatingDto);
    }
}
