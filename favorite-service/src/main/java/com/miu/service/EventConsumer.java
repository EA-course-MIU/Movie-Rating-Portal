package com.miu.service;

import com.miu.dto.MediaDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface EventConsumer {
    void receiveDeleteSeries(ConsumerRecord<String, MediaDto> cr, MediaDto mediaDto);
    void receiveDeleteMovie(ConsumerRecord<String, MediaDto> cr, MediaDto mediaDto);
    void receiveDeleteUser(ConsumerRecord<String, String> cr, String userId);
}
