package com.example.service.impl;

import com.example.dto.EpisodeDto;
import com.example.dto.RequestEpisodeDto;
import com.example.entity.Episode;
import com.example.mapper.EpisodeMapper;
import com.example.repo.EpisodeRepo;
import com.example.repo.SeasonRepo;
import com.example.repo.SeriesRepo;
import com.example.service.EpisodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EpisodeServiceImpl implements EpisodeService {

    private final EpisodeRepo episodeRepo;

    private final EpisodeMapper episodeMapper;

    private final SeasonRepo seasonRepo;
    @Override
    public EpisodeDto getById(int id) {
        return episodeMapper.toDto(episodeRepo.findById(id).get());
    }

    @Override
    public List<EpisodeDto> getAll(int seasonId) {
        var season = seasonRepo.findById(seasonId).get();
        return episodeMapper.toListDto(season.getEpisodes());
    }
    @Transactional
    @Override
    public EpisodeDto save(int seasonId, RequestEpisodeDto episodeDto) {
        var season = seasonRepo.findById(seasonId).get();
        var newEpisode = new Episode(episodeDto.getName(), episodeDto.getEpisodeNumber(), episodeDto.getDuration(), season, "1");
        episodeRepo.save(newEpisode);
        return episodeMapper.toDto(newEpisode);
    }

    @Transactional
    @Override
    public EpisodeDto update(int id, RequestEpisodeDto episodeDto) {
        var updatingEpisode = episodeRepo.findById(id).get();
        if(updatingEpisode==null) return null;
        if(episodeDto.getName()!=null) updatingEpisode.setName(episodeDto.getName());
        if(episodeDto.getEpisodeNumber()!=null) updatingEpisode.setEpisodeNumber(episodeDto.getEpisodeNumber());
        if(episodeDto.getDuration()!=null) updatingEpisode.setDuration(episodeDto.getDuration());
        return episodeMapper.toDto(updatingEpisode);
    }

    @Transactional
    @Override
    public void delete(int id) {
        episodeRepo.deleteById(id);
    }
}
