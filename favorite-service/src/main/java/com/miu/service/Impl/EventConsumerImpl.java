package com.miu.service.Impl;

import com.miu.dto.MediaDto;
import com.miu.repo.FavoriteListRepo;
import com.miu.repo.FavoriteMediaRepo;
import com.miu.service.EventConsumer;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventConsumerImpl implements EventConsumer {
    private final FavoriteMediaRepo favoriteMediaRepo;
    private final FavoriteListRepo favoriteListRepo;

    @KafkaListener(topics = "series-delete-topic", groupId = "favorite-service")
    @Override
    public void receiveDeleteSeries(ConsumerRecord<String, MediaDto> cr, @Payload MediaDto mediaDto) {
        favoriteMediaRepo.deleteAllByMediaId(mediaDto.getType(), mediaDto.getId());
    }

    @KafkaListener(topics = "movie-delete-topic", groupId = "favorite-service")
    @Override
    public void receiveDeleteMovie(ConsumerRecord<String, MediaDto> cr, @Payload MediaDto mediaDto) {
        favoriteMediaRepo.deleteAllByMediaId(mediaDto.getType(), mediaDto.getId());
    }

    @KafkaListener(topics = "remove-user-topic", groupId = "favorite-service")
    @Override
    public void receiveDeleteUser(ConsumerRecord<String, String> cr, @Payload String userId) {
        favoriteListRepo.deleteAllByUserIdIs(userId);
    }


}
