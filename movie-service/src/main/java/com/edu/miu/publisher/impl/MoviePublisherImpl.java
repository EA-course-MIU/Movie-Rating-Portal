package com.edu.miu.publisher.impl;

import com.edu.miu.dto.message.MediaDto;
import com.edu.miu.publisher.MoviePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MoviePublisherImpl implements MoviePublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendRemovedMovieMessage(MediaDto mediaDto) {
        kafkaTemplate.send("remove-media-topic", mediaDto);
    }
}
