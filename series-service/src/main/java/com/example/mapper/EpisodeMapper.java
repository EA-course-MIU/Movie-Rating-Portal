package com.example.mapper;

import com.example.dto.EpisodeDto;
import com.example.entity.Episode;
import org.springframework.stereotype.Component;

@Component
public class EpisodeMapper  extends Mapper<Episode, EpisodeDto> {
    public EpisodeMapper() {
        super(Episode.class, EpisodeDto.class);
    }
}
