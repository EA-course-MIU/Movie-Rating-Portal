package com.example;

import com.example.entity.Series;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
public class SeriesServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeriesServiceApplication.class, args);
    }

}