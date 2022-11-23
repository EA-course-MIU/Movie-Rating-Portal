package com.example.service;

import com.example.dto.EpisodeDto;
import com.example.dto.RequestEpisodeDto;

import javax.transaction.Transactional;
import java.util.List;

public interface EpisodeService {
    EpisodeDto getById(int id);
    List<EpisodeDto> getAll(int seasonId);
    EpisodeDto save(int seasonId, RequestEpisodeDto episodeDto);
    EpisodeDto update(int id, RequestEpisodeDto episodeDto);
    void delete(int id);
}
