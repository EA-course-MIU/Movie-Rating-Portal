package com.edu.mapper;

import com.edu.entity.Episode;
import com.edu.dto.EpisodeDto;
import org.springframework.stereotype.Component;

@Component
public class EpisodeMapper  extends Mapper<Episode, EpisodeDto> {
    public EpisodeMapper() {
        super(Episode.class, EpisodeDto.class);
    }
}
