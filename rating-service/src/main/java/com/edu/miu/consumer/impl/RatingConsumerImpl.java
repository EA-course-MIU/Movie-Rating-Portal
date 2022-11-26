package com.edu.miu.consumer.impl;

import com.edu.miu.consumer.RatingConsumer;
import com.edu.miu.dto.RatingDto;
import com.edu.miu.dto.message.MediaDto;
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
            topics = "remove-media-topic",
            containerFactory = "removeMediaKafkaListenerContainerFactory",
            groupId = "remove-media-topic")
    @Override
    public void receiveRemovedMediaMessage(MediaDto mediaDto) {
        if (mediaDto.getMediaId() > 0 && mediaDto.getMediaType() != null) {
            ratingService.deleteRatingByMedia(mediaDto.getMediaId(), mediaDto.getMediaType());
        }
    }

    @Override
    @RabbitListener(queues = {"remove-media-queue"})
    public void receiveRemovedMediaRabbitMessage(MediaDto mediaDto) {
        if (mediaDto.getMediaId() > 0 && mediaDto.getMediaType() != null) {
            ratingService.deleteRatingByMedia(mediaDto.getMediaId(), mediaDto.getMediaType());
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
