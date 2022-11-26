package com.example.mapper;
import com.example.dto.GenreDto;
import com.example.dto.PersonDto;
import com.example.entity.Genre;
import com.example.entity.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreMapper {
    private final ModelMapper modelMapper;

    public GenreDto toDto(Genre genre) {
        return modelMapper.map(genre, GenreDto.class);
    }

    public List <GenreDto> toDtos(List<Genre> genres) {
        return genres.stream()
                .map(this::toDto)
                .toList();
    }

    public Genre toEntity(GenreDto genreDto) {
        return modelMapper.map(genreDto,Genre.class);
    }

    public List<Genre> toEntities(List<GenreDto> genreDtos) {
        return genreDtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
