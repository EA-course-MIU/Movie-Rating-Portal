package com.example.controller;

import com.example.dto.EpisodeDto;
import com.example.dto.RequestEpisodeDto;
import com.example.dto.RequestSeasonDto;
import com.example.dto.SeasonDto;
import com.example.service.EpisodeService;
import com.example.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seasons")
public class SeasonController {
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private EpisodeService episodeService;
    @GetMapping("/{id}")
    public SeasonDto getById(@PathVariable int id){
        return seasonService.getById(id);
    }

    @PutMapping("/{id}")
    public SeasonDto update(@PathVariable int id, @RequestBody RequestSeasonDto seasonDto){
        return seasonService.update(id, seasonDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        seasonService.delete(id);
    }

    @PostMapping("/{seasonId}/episodes")
    public EpisodeDto addEpisode(@PathVariable int seasonId, @RequestBody RequestEpisodeDto episodeDto){
        return episodeService.save(seasonId, episodeDto);
    }

    @GetMapping("/{seasonId}/episodes")
    public List<EpisodeDto> getEpisodesBySeasonId(@PathVariable int seasonId){
        return episodeService.getAll(seasonId);
    }
}
