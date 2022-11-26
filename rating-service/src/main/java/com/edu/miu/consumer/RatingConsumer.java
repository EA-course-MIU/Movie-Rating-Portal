package com.edu.miu.consumer;

import com.edu.miu.dto.RatingDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface RatingConsumer {

    void receiveMessageFromMovie(RatingDto ratingDto);


    void receiveRabbitMessageFromMovie(RatingDto ratingDto);


    void receiveMessageFromUser(ConsumerRecord<String, String> cr, String userId);

}
