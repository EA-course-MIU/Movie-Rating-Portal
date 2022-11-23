package com.example.controller;

import com.example.dto.Filter.FilterDto;
import com.example.dto.RequestSeasonDto;
import com.example.dto.RequestSeriesDto;
import com.example.dto.SeasonDto;
import com.example.dto.SeriesDto;
import com.example.entity.Episode;
import com.example.entity.Season;
import com.example.entity.Series;
import com.example.service.EpisodeService;
import com.example.service.SeasonService;
import com.example.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {
    @Autowired
    private SeriesService seriesService;
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private EpisodeService episodeService;

    @GetMapping
    public List<SeriesDto> getAll(){
        return seriesService.getAll();
    }

    @GetMapping("/{id}")
    public SeriesDto getById(@PathVariable int id){
        return seriesService.getById(id);
    }

    @PostMapping
    public SeriesDto saveSeries(@RequestBody RequestSeriesDto series){
        return seriesService.saveSeries(series);
    }

    @PutMapping("/{id}")
    public SeriesDto updateSeries(@PathVariable int id, @RequestBody RequestSeriesDto series){
        return seriesService.updateSeries(id, series);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        seriesService.deleteById(id);
    }

    @GetMapping("/filter")
    public List<SeriesDto> filterSeries(FilterDto filter){
        return seriesService.filter(filter);
    }

    @GetMapping("/{seriesId}/seasons")
    public List<SeasonDto> getSeasonsBySeriesId(@PathVariable int seriesId){
        return seasonService.getAll(seriesId);
    }

    @PostMapping("/{seriesId}/seasons")
    public SeasonDto saveSeason(@PathVariable int seriesId, @RequestBody RequestSeasonDto season){
        return seasonService.create(seriesId, season);
    }
}
