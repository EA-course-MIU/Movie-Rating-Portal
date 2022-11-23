package com.example.mapper;

import com.example.dto.SeriesDto;
import com.example.entity.Series;
import org.springframework.stereotype.Component;

@Component
public class SeriesMapper extends Mapper<Series, SeriesDto> {
    public SeriesMapper() {
        super(Series.class, SeriesDto.class);
    }
}
