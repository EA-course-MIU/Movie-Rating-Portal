package com.miu.service.impl;


import com.miu.repo.EpisodeRepo;
import com.miu.repo.SeasonRepo;
import com.miu.repo.SeriesRepo;
import com.miu.service.EventConsumer;
import com.miu.service.SeriesService;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventConsumerImpl implements EventConsumer {

    private final SeriesRepo seriesRepo;
    private final SeasonRepo seasonRepo;
    private final EpisodeRepo episodeRepo;

    @KafkaListener(topics = "user-delete-topic", groupId = "series-service")
    @Override
    public void receiveDeleteUser(ConsumerRecord<String, String> cr, String userId) {
        seriesRepo.deleteAllByOwnerIdIs(userId);
        seasonRepo.deleteAllByOwnerIdIs(userId);
        episodeRepo.deleteAllByOwnerIdIs(userId);
    }
}
