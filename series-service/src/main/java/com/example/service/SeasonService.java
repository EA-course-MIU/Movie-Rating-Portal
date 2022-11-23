package com.example.service;

import com.example.dto.RequestSeasonDto;
import com.example.dto.SeasonDto;

import java.util.List;

public interface SeasonService {
    SeasonDto getById(int seasonId);
    List<SeasonDto> getAll(int seriesId);
    SeasonDto create(int seriesId, RequestSeasonDto seasonDto);
    SeasonDto update(int seasonId, RequestSeasonDto seasonDto);
    void delete(int seasonId);
}
