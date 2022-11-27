package com.edu.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface EventConsumer {

    void receiveDeleteUser(ConsumerRecord<String, String> cr, String userId);

}
