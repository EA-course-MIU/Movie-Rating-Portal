package com.miu.service;

import com.miu.dto.EpisodeDto;
import com.miu.dto.RequestEpisodeDto;

import java.util.List;

public interface EpisodeService {
    EpisodeDto getById(int id);
    List<EpisodeDto> getAll(int seasonId);
    EpisodeDto save(int seasonId, RequestEpisodeDto episodeDto);
    EpisodeDto update(int id, RequestEpisodeDto episodeDto);
    void delete(int id);
}
