package com.edu.miu.consumer;

import com.edu.miu.dto.MediaDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface RatingConsumer {

    void receiveRemovedMediaMessage(MediaDto mediaDto);


    void receiveRemovedMediaRabbitMessage(MediaDto mediaDto);


    void receiveMessageFromUser(ConsumerRecord<String, String> cr, String userId);

}
