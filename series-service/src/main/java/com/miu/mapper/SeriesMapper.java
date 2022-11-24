package com.miu.mapper;

import com.miu.dto.PersonDto;
import com.miu.entity.Series;
import com.miu.service.MetadataClient;
import com.miu.dto.GenreDto;
import com.miu.dto.SeriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeriesMapper extends Mapper<Series, SeriesDto> {

    @Autowired
    private MetadataClient metadataClient;

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
            genres.add(metadataClient.getGenreById(genreId));
        });
        List<PersonDto> directors = new ArrayList<>();
        seriesDto.setDirectors(directors);
        t.getDirectorIds().stream().forEach(d -> {
            int directorId = d.getMediaPersonKey().getPersonId();
            directors.add(metadataClient.getPersonById(directorId));
        });
        List<PersonDto> actors = new ArrayList<>();
        seriesDto.setActors(actors);
        t.getActorIds().stream().forEach(a -> {
            int actorId = a.getMediaPersonKey().getPersonId();
            actors.add(metadataClient.getPersonById(actorId));
        });
        return seriesDto;
    }
}
