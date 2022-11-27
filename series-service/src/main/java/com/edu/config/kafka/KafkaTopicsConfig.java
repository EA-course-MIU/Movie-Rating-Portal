package com.edu.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicsConfig {


    @Bean
    public NewTopic seriesDeleteTopic() {
        return new NewTopic("series-delete-topic", 1, (short) 1);
    }


}
