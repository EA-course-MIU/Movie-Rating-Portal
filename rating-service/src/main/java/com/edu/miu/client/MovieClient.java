package com.edu.miu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MOVIE-SERVICE", path = "/movies")
public interface MovieClient {

    @GetMapping("/{id}")
    Object getMovieById(@PathVariable int id);

}
