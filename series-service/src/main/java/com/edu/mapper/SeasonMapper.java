package com.edu.mapper;

import com.edu.entity.Season;
import com.edu.dto.SeasonDto;
import org.springframework.stereotype.Component;

@Component
public class SeasonMapper extends Mapper<Season, SeasonDto> {
    public SeasonMapper() {
        super(Season.class, SeasonDto.class);
    }
}
