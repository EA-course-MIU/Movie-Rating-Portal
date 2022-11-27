package com.edu.service.impl;

import com.edu.dto.SeasonDto;
import com.edu.entity.Season;
import com.edu.mapper.SeasonMapper;
import com.edu.repo.SeasonRepo;
import com.edu.repo.SeriesRepo;
import com.edu.service.SeasonService;
import com.edu.dto.RequestSeasonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepo seasonRepo;

    private final SeriesRepo seriesRepo;

    private final SeasonMapper seasonMapper;

    @Override
    public SeasonDto getById(int seasonId) {
        return seasonMapper.toDto(seasonRepo.findById(seasonId).get());
    }

    @Override
    public List<SeasonDto> getAll(int seriesId) {
        var series = seriesRepo.findById(seriesId).get();
        return seasonMapper.toListDto(series.getSeasons());
    }

    @Transactional
    @Override
    public SeasonDto create(int seriesId, RequestSeasonDto seasonDto) {
        var series = seriesRepo.findById(seriesId).get();
        var season = new Season(seasonDto.getName(), seasonDto.getSeasonNumber(), seasonDto.getYear(), series, new ArrayList<>(), "1");
        series.getSeasons().add(season);
        seasonRepo.save(season);
        return seasonMapper.toDto(season);
    }

    @Transactional
    @Override
    public SeasonDto update(int seasonId, RequestSeasonDto seasonDto) {
        var updatingSeason = seasonRepo.findById(seasonId).get();
        if(seasonDto.getName() != null){
            updatingSeason.setName(seasonDto.getName());
        }
        if(seasonDto.getSeasonNumber() != null){
            updatingSeason.setSeasonNumber(seasonDto.getSeasonNumber());
        }
        if(seasonDto.getYear() != null){
            updatingSeason.setYear(seasonDto.getYear());
        }
        return seasonMapper.toDto(updatingSeason);
    }

    @Transactional
    @Override
    public void delete(int seasonId) {
        seasonRepo.deleteById(seasonId);
    }
}
