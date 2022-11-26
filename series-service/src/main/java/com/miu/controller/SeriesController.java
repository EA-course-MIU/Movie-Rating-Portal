package com.miu.controller;

import com.miu.dto.SeasonDto;
import com.miu.dto.SeriesDto;
import com.miu.service.JwtService;
import com.miu.service.SeriesService;
import com.miu.dto.Filter.FilterDto;
import com.miu.dto.RequestSeasonDto;
import com.miu.dto.RequestSeriesDto;
import com.miu.service.EpisodeService;
import com.miu.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {
    @Autowired
    private SeriesService seriesService;
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private JwtService jwtService;

    @GetMapping
    public List<SeriesDto> getAll() {
        return seriesService.getAll();
    }

    @GetMapping("/{id}")
    public SeriesDto getById(@PathVariable int id) {
        return seriesService.getById(id);
    }

    @PostMapping
    public SeriesDto saveSeries(@RequestBody RequestSeriesDto series) {
        return seriesService.saveSeries(series);
    }

    @PutMapping("/{id}")
    public SeriesDto updateSeries(@PathVariable int id, @RequestBody RequestSeriesDto series, @RequestHeader String authorization) {
        return seriesService.updateSeries(id, series, jwtService.getUserIdFromToken(authorization));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id, @RequestHeader String authorization) {
        seriesService.deleteById(id, jwtService.getUserIdFromToken(authorization));
    }

    @GetMapping("/filter")
    public List<SeriesDto> filterSeries(FilterDto filter) {
        return seriesService.filter(filter);
    }

    @GetMapping("/{seriesId}/seasons")
    public List<SeasonDto> getSeasonsBySeriesId(@PathVariable int seriesId) {
        return seasonService.getAll(seriesId);
    }

    @PostMapping("/{seriesId}/seasons")
    public SeasonDto saveSeason(@PathVariable int seriesId, @RequestBody RequestSeasonDto season) {
        return seasonService.create(seriesId, season);
    }
}
