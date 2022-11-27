package com.edu.controller;

import com.edu.dto.EpisodeDto;
import com.edu.dto.RequestEpisodeDto;
import com.edu.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
