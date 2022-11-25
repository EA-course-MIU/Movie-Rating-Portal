package com.miu.service.impl;

import com.miu.dto.MediaDto;
import com.miu.enums.MediaType;
import com.miu.service.SeriesPublisher;
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
