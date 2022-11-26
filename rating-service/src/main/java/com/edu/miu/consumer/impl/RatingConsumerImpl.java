package com.edu.miu.consumer.impl;

import com.edu.miu.consumer.RatingConsumer;
import com.edu.miu.dto.RatingDto;
import com.edu.miu.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingConsumerImpl implements RatingConsumer {

    private final RatingService ratingService;

    @KafkaListener(
            topics = "remove-movie-topic",
            containerFactory = "removeRatingKafkaListenerContainerFactory",
            groupId = "remove-movie-topic")
    @Override
    public void receiveMessageFromMovie(RatingDto ratingDto) {
        if (ratingDto.getMediaId() > 0 && ratingDto.getMediaType() != null) {
            ratingService.deleteRatingByMedia(ratingDto.getMediaId(), ratingDto.getMediaType());
        }
    }

    @Override
    @RabbitListener(queues = {"remove-movie-queue"})
    public void receiveRabbitMessageFromMovie(RatingDto ratingDto) {
        if (ratingDto.getMediaId() > 0 && ratingDto.getMediaType() != null) {
            ratingService.deleteRatingByMedia(ratingDto.getMediaId(), ratingDto.getMediaType());
        }
    }

    @KafkaListener(
            topics = "remove-user-topic",
            containerFactory = "kafkaListenerStringContainerFactory",
            groupId = "remove-user-topic")
    @Override
    public void receiveMessageFromUser(ConsumerRecord<String, String> cr, @Payload String userId) {
        if (StringUtils.isNotBlank(userId)) {
            ratingService.deleteAllRatingByUserId(userId.replaceAll("^\"|\"$", ""));
        }
    }
}
