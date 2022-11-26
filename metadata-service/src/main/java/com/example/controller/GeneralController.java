package com.example.controller;

import com.example.dto.GenreDto;
import com.example.dto.MetaDataDto;
import com.example.dto.criteria.MetaDataCriteria;
import com.example.dto.PersonDto;
import com.example.service.GenreService;
import com.example.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/metadata")
public class GeneralController {

    private final GenreService genreService;

    private final PersonService personService;


    @GetMapping
    public MetaDataDto getAll(MetaDataCriteria metaDataCriteria){
        List<Integer> genreIds = metaDataCriteria.getGenreIds();
        List<Integer> directorIds = metaDataCriteria.getDirectorIds();
        List<Integer> actorIds = metaDataCriteria.getActorIds();

        List<GenreDto> genres = new ArrayList<>();
        List<PersonDto> directors = new ArrayList<>();
        List<PersonDto> actors = new ArrayList<>();

        if (genreIds != null && genreIds.size() > 0) {
            genres.addAll(genreService.getByIds(genreIds));
        }

        if (directorIds != null && directorIds.size() > 0) {
            directors.addAll(personService.getByIds(directorIds));
        }

        if (actorIds != null && actorIds.size() > 0) {
            actors.addAll(personService.getByIds(actorIds));
        }

        return new MetaDataDto(genres, directors, actors);
    }

}
