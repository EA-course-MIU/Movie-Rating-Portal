package com.edu.service.impl;

import com.edu.dto.EpisodeDto;
import com.edu.entity.Episode;
import com.edu.exception.BadRequestException;
import com.edu.exception.ResourceNotFoundException;
import com.edu.mapper.EpisodeMapper;
import com.edu.repo.EpisodeRepo;
import com.edu.repo.SeasonRepo;
import com.edu.service.EpisodeService;
import com.edu.dto.RequestEpisodeDto;
import lombok.RequiredArgsConstructor;
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
        var episode = episodeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Episode not found"));
        return episodeMapper.toDto(episode);
    }

    @Override
    public List<EpisodeDto> getAll(int seasonId) {
        var season = seasonRepo.findById(seasonId).get();
        return episodeMapper.toListDto(season.getEpisodes());
    }

    @Transactional
    @Override
    public EpisodeDto save(int seasonId, RequestEpisodeDto episodeDto, String userId) {
        var season = seasonRepo.findById(seasonId).orElseThrow(() -> new ResourceNotFoundException("Season not found"));
        var newEpisode = new Episode(episodeDto.getName(), episodeDto.getEpisodeNumber(), episodeDto.getDuration(), season, userId);
        episodeRepo.save(newEpisode);
        return episodeMapper.toDto(newEpisode);
    }

    @Transactional
    @Override
    public EpisodeDto update(int id, RequestEpisodeDto episodeDto, String userId) {
        var updatingEpisode = episodeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Episode not found"));
        if (!updatingEpisode.getOwnerId().equals(userId)) throw new BadRequestException("You can't update this episode");
        if (episodeDto.getName() != null) updatingEpisode.setName(episodeDto.getName());
        if (episodeDto.getEpisodeNumber() != null) updatingEpisode.setEpisodeNumber(episodeDto.getEpisodeNumber());
        if (episodeDto.getDuration() != null) updatingEpisode.setDuration(episodeDto.getDuration());
        return episodeMapper.toDto(updatingEpisode);
    }

    @Transactional
    @Override
    public void delete(int id, String userId) {
        var deletingEpisode = episodeRepo.findById(id).get();
        if (deletingEpisode == null || !deletingEpisode.getOwnerId().equals(userId)) return;
        episodeRepo.deleteById(id);
    }
}
