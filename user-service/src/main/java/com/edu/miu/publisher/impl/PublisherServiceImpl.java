package com.edu.miu.publisher.impl;

import com.edu.miu.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendDeleteUserMessage(String userId) {
        kafkaTemplate.send("remove-user-topic", userId);
    }
}
