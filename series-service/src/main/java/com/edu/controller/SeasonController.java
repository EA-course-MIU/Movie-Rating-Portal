package com.edu.controller;

import com.edu.dto.EpisodeDto;
import com.edu.dto.SeasonDto;
import com.edu.service.EpisodeService;
import com.edu.service.JwtService;
import com.edu.service.SeasonService;
import com.edu.dto.RequestEpisodeDto;
import com.edu.dto.RequestSeasonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seasons")
public class SeasonController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private EpisodeService episodeService;
    @GetMapping("/{id}")
    public SeasonDto getById(@PathVariable int id){
        return seasonService.getById(id);
    }

    @PutMapping("/{id}")
    public SeasonDto update(@PathVariable int id, @RequestBody RequestSeasonDto seasonDto, @RequestHeader String authorization){
        return seasonService.update(id, seasonDto, jwtService.getUserIdFromToken(authorization));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, @RequestHeader String authorization){
        seasonService.delete(id, jwtService.getUserIdFromToken(authorization));
    }

    @PostMapping("/{seasonId}/episodes")
    public EpisodeDto addEpisode(@PathVariable int seasonId, @RequestBody RequestEpisodeDto episodeDto, @RequestHeader String authorization){
        return episodeService.save(seasonId, episodeDto, jwtService.getUserIdFromToken(authorization));
    }

    @GetMapping("/{seasonId}/episodes")
    public List<EpisodeDto> getEpisodesBySeasonId(@PathVariable int seasonId){
        return episodeService.getAll(seasonId);
    }
}
