package com.edu.controller;

import com.edu.dto.EpisodeDto;
import com.edu.dto.RequestEpisodeDto;
import com.edu.service.EpisodeService;
import com.edu.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/episodes")
@RestController
public class EpisodeController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/{id}")
    public EpisodeDto getById(@PathVariable int id) {
        return episodeService.getById(id);
    }

    @PutMapping("/{id}")
    public EpisodeDto update(@PathVariable int id, @RequestBody RequestEpisodeDto episodeDto, @RequestHeader String authorization) {
        return episodeService.update(id, episodeDto, jwtService.getUserIdFromToken(authorization));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, @RequestHeader String authorization) {
        episodeService.delete(id, jwtService.getUserIdFromToken(authorization));
    }
}
