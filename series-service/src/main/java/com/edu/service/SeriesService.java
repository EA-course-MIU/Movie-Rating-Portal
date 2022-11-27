package com.edu.service;

import com.edu.dto.Filter.FilterDto;
import com.edu.dto.RequestSeriesDto;
import com.edu.dto.SeriesDto;

import java.util.List;

public interface SeriesService {
    SeriesDto getById(int id);
    List<SeriesDto> getAll();
    void deleteById(int id, String userId);
    SeriesDto updateSeries(int id, RequestSeriesDto series, String userId);
    SeriesDto saveSeries(RequestSeriesDto series, String userId);
    List<SeriesDto> filter(FilterDto filterDto);
    SeriesDto updateRating(int id, double rating);
}
