package com.edu.service;

import com.edu.dto.GenreDto;
import com.edu.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "METADATA-SERVICE")
public interface MetadataClient {
    @RequestMapping(method = RequestMethod.GET, value = "/genres/{genreId}")
    GenreDto getGenreById(@PathVariable("genreId") Integer genreId);

    @RequestMapping(method = RequestMethod.GET, value = "/persons/{personId}")
    PersonDto getPersonById(@PathVariable("personId") Integer personId);
}
