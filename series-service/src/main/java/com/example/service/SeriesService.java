package com.example.service;

import com.example.dto.Filter.FilterDto;
import com.example.dto.RequestSeriesDto;
import com.example.dto.SeriesDto;
import com.example.entity.Series;

import java.util.List;

public interface SeriesService {
    SeriesDto getById(int id);
    List<SeriesDto> getAll();
    void deleteById(int id);
    SeriesDto updateSeries(int id, RequestSeriesDto series);
    SeriesDto saveSeries(RequestSeriesDto series);

    List<SeriesDto> filter(FilterDto filterDto);
    SeriesDto updateRating(int id, double rating);
}
