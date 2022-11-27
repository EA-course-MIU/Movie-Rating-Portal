package com.edu.service;

import com.edu.dto.EpisodeDto;
import com.edu.dto.RequestEpisodeDto;

import java.util.List;

public interface EpisodeService {
    EpisodeDto getById(int id);
    List<EpisodeDto> getAll(int seasonId);
    EpisodeDto save(int seasonId, RequestEpisodeDto episodeDto, String userId);
    EpisodeDto update(int id, RequestEpisodeDto episodeDto, String userId);
    void delete(int id, String userId);
}
