package com.edu.miu.service.impl;

import com.edu.miu.client.MetaDataClient;
import com.edu.miu.dto.GenreDto;
import com.edu.miu.dto.MetaDataDto;
import com.edu.miu.dto.PersonDto;
import com.edu.miu.dto.criteria.MetaDataCriteria;
import com.edu.miu.enums.PositionType;
import com.edu.miu.service.MetaDataService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetaDataServiceImpl implements MetaDataService {

    private final CircuitBreakerFactory breakerFactory;

    private final MetaDataClient metaDataClient;

    private final ModelMapper modelMapper;

    @Override
    public MetaDataDto getMetaDataDto(MetaDataCriteria metaDataCriteria) {
        CircuitBreaker circuitBreaker = breakerFactory.create("metadata-fetching");
        Object data = circuitBreaker.run(() -> metaDataClient.getAll(metaDataCriteria), throwable -> null);
        return data == null ? new MetaDataDto() : modelMapper.map(data, MetaDataDto.class);
    }

    @Override
    public List<GenreDto> getGenres(List<Integer> ids) {
        CircuitBreaker circuitBreaker = breakerFactory.create("genre-fetching");
        List<Object> genres = circuitBreaker.run(() -> metaDataClient.getGenres(ids), throwable -> null);
        return genres == null ? Collections.EMPTY_LIST : genres.stream().map(g -> modelMapper.map(g, GenreDto.class)).toList();
    }

    @Override
    public List<PersonDto> getPersons(String position, List<Integer> ids) {
        CircuitBreaker circuitBreaker = breakerFactory.create("person-fetching");
        List<Object> actors = circuitBreaker.run(() -> metaDataClient.getPersons(position, ids), throwable -> null);
        return actors == null ? Collections.EMPTY_LIST : actors.stream().map(p -> modelMapper.map(p, PersonDto.class)).toList();

    }
}
