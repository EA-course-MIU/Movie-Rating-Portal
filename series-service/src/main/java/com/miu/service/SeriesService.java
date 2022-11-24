package com.miu.service;

import com.miu.dto.Filter.FilterDto;
import com.miu.dto.RequestSeriesDto;
import com.miu.dto.SeriesDto;

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
