package com.edu.service.impl;

import com.edu.dto.GenreDto;
import com.edu.service.MetadataService;
import com.edu.dto.PersonDto;
import com.edu.service.MetadataClient;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MetadataServiceImpl implements MetadataService {
    private final MetadataClient metadataClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Override
    public PersonDto getPersonById(int id) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("person-fetching");
        return circuitBreaker.run(() -> metadataClient.getPersonById(id), throwable -> null);
    }

    @Override
    public GenreDto getGenreById(int id) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("genre-fetching");
        return circuitBreaker.run(() -> metadataClient.getGenreById(id), throwable -> null);
    }

}
