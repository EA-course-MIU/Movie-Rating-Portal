package com.example.controller;

import com.example.dto.EpisodeDto;
import com.example.dto.RequestEpisodeDto;
import com.example.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/episodes")
@RestController
public class EpisodeController {
    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/{id}")
    public EpisodeDto getById(@PathVariable int id){
        return episodeService.getById(id);
    }

    @PutMapping("/{id}")
    public EpisodeDto update(@PathVariable int id, @RequestBody RequestEpisodeDto episodeDto){
        return episodeService.update(id, episodeDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        episodeService.delete(id);
    }
}
