package com.edu.miu.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicsConfig {


    @Bean
    public NewTopic adviceTopic() {
        return new NewTopic("remove-rating-topic", 1, (short) 1);
    }


}
