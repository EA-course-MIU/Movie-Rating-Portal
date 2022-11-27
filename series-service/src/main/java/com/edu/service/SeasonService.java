package com.edu.service;

import com.edu.dto.SeasonDto;
import com.edu.dto.RequestSeasonDto;

import java.util.List;

public interface SeasonService {
    SeasonDto getById(int seasonId);
    List<SeasonDto> getAll(int seriesId);
    SeasonDto create(int seriesId, RequestSeasonDto seasonDto, String userId);
    SeasonDto update(int seasonId, RequestSeasonDto seasonDto, String userId);
    void delete(int seasonId, String userId);
}
