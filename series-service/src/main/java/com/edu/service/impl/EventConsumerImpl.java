package com.edu.service.impl;


import com.edu.repo.SeriesRepo;
import com.edu.service.EventConsumer;
import com.edu.repo.EpisodeRepo;
import com.edu.repo.SeasonRepo;
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

    @KafkaListener(topics = "remove-user-topic", groupId = "series-service")
    @Override
    public void receiveDeleteUser(ConsumerRecord<String, String> cr, String userId) {
        seriesRepo.deleteAllByOwnerIdIs(userId);
        seasonRepo.deleteAllByOwnerIdIs(userId);
        episodeRepo.deleteAllByOwnerIdIs(userId);
    }
}
