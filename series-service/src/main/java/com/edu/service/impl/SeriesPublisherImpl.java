package com.edu.service.impl;

import com.edu.dto.MediaDto;
import com.edu.enums.MediaType;
import com.edu.service.SeriesPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SeriesPublisherImpl implements SeriesPublisher {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Override
    public void sendSeriesDeleteMessage(int id) {
        kafkaTemplate.send("series-delete-topic", new MediaDto(id, MediaType.TV_SERIES));
    }
}
