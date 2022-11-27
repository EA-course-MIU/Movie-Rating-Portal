package com.edu.mapper;

import com.edu.entity.Series;
import com.edu.dto.PersonDto;
import com.edu.dto.GenreDto;
import com.edu.dto.SeriesDto;
import com.edu.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeriesMapper extends Mapper<Series, SeriesDto> {


    @Autowired
    private MetadataService metadataService;

    public SeriesMapper() {
        super(Series.class, SeriesDto.class);
    }

    @Override
    public SeriesDto toDto(Series t) {
        SeriesDto seriesDto = super.toDto(t);
        List<GenreDto> genres = new ArrayList<>();
        seriesDto.setGenres(genres);
        t.getGenres().stream().forEach(genre -> {
            int genreId = genre.getMediaGenreKey().getGenreId();
            GenreDto genreDto = metadataService.getGenreById(genreId);
            if(genreDto != null) genres.add(genreDto);
        });
        List<PersonDto> directors = new ArrayList<>();
        seriesDto.setDirectors(directors);
        t.getDirectorIds().stream().forEach(d -> {
            int directorId = d.getMediaPersonKey().getPersonId();
            PersonDto personDto = metadataService.getPersonById(directorId);
            if(personDto != null) directors.add(personDto);
        });
        List<PersonDto> actors = new ArrayList<>();
        seriesDto.setActors(actors);
        t.getActorIds().stream().forEach(a -> {
            int actorId = a.getMediaPersonKey().getPersonId();
            PersonDto personDto = metadataService.getPersonById(actorId);
            if(personDto != null) actors.add(personDto);
        });
        return seriesDto;
    }
}
