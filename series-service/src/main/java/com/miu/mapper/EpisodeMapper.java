package com.miu.mapper;

import com.miu.entity.Episode;
import com.miu.dto.EpisodeDto;
import org.springframework.stereotype.Component;

@Component
public class EpisodeMapper  extends Mapper<Episode, EpisodeDto> {
    public EpisodeMapper() {
        super(Episode.class, EpisodeDto.class);
    }
}
