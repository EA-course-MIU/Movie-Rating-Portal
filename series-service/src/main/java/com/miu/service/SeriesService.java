package com.miu.service;

import com.miu.dto.Filter.FilterDto;
import com.miu.dto.RequestSeriesDto;
import com.miu.dto.SeriesDto;

import java.util.List;

public interface SeriesService {
    SeriesDto getById(int id);
    List<SeriesDto> getAll();
    void deleteById(int id, String userId);
    SeriesDto updateSeries(int id, RequestSeriesDto series, String userId);
    SeriesDto saveSeries(RequestSeriesDto series);
    List<SeriesDto> filter(FilterDto filterDto);
    SeriesDto updateRating(int id, double rating);
}
