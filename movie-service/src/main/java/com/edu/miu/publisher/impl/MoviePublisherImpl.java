package com.edu.miu.publisher.impl;

import com.edu.miu.dto.RatingDto;
import com.edu.miu.publisher.MoviePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MoviePublisherImpl implements MoviePublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendMessageToRemoveRating(RatingDto ratingDto) {
        kafkaTemplate.send("remove-rating-topic", ratingDto);
    }
}
