package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
@EnableDiscoveryClient
public class APIGateway {
    public static void main(String[] args) {
        SpringApplication.run(APIGateway.class, args);
    }

    @Bean
    public DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator(ReactiveDiscoveryClient dc, DiscoveryLocatorProperties dlp) {
        dlp.setLowerCaseServiceId(true);
        return new DiscoveryClientRouteDefinitionLocator(dc, dlp);
    }
}