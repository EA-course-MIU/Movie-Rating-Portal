package com.miu.mapper;

import com.miu.entity.Season;
import com.miu.dto.SeasonDto;
import org.springframework.stereotype.Component;

@Component
public class SeasonMapper extends Mapper<Season, SeasonDto> {
    public SeasonMapper() {
        super(Season.class, SeasonDto.class);
    }
}
