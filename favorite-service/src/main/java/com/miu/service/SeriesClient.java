package com.miu.service;

import com.miu.dto.SeriesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("SERIES-SERVICE")
public interface SeriesClient {
    @RequestMapping(method = RequestMethod.GET, value="/series/{seriesId}")
    SeriesDto getSeriesById(@PathVariable int seriesId);
}
