package com.example;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
public class CommentServiceApplication {
    public static void main (String[] args) {
        SpringApplication.run(CommentServiceApplication.class,args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
