package com.example.mapper;

import com.example.dto.SeasonDto;
import com.example.entity.Season;
import org.springframework.stereotype.Component;

@Component
public class SeasonMapper extends Mapper<Season, SeasonDto> {
    public SeasonMapper() {
        super(Season.class, SeasonDto.class);
    }
}
