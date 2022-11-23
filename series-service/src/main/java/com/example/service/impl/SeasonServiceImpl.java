package com.example.service.impl;

import com.example.dto.RequestSeasonDto;
import com.example.dto.SeasonDto;
import com.example.entity.Season;
import com.example.mapper.SeasonMapper;
import com.example.repo.SeasonRepo;
import com.example.repo.SeriesRepo;
import com.example.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
public class SeasonServiceImpl implements SeasonService {
    @Autowired
    private SeasonRepo seasonRepo;
    @Autowired
    private SeriesRepo seriesRepo;
    @Autowired
    private SeasonMapper seasonMapper;

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
        var season = new Season(seasonDto.getName(), seasonDto.getSeasonNumber(), seasonDto.getYear(), series, new ArrayList<>(), 1);
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
