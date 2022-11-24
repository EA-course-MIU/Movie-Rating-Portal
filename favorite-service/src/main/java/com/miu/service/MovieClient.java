package com.miu.service;

import com.miu.dto.MovieDto;
import com.miu.dto.SeriesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("MOVIE-SERVICE")
public interface MovieClient {
    @RequestMapping(method = RequestMethod.GET, value="/movie/{movieId}")
    MovieDto getMovieById(@PathVariable int movieId);
}
