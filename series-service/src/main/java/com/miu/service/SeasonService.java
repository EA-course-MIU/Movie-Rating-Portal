package com.miu.service;

import com.miu.dto.SeasonDto;
import com.miu.dto.RequestSeasonDto;

import java.util.List;

public interface SeasonService {
    SeasonDto getById(int seasonId);
    List<SeasonDto> getAll(int seriesId);
    SeasonDto create(int seriesId, RequestSeasonDto seasonDto);
    SeasonDto update(int seasonId, RequestSeasonDto seasonDto);
    void delete(int seasonId);
}
