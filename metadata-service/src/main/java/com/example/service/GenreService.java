package com.example.service;

import com.example.dto.GenreDto;
import com.example.entity.Genre;

import java.util.List;

public interface GenreService {
    List <GenreDto> getAll();
    GenreDto getById(int id);

    GenreDto addGenre (GenreDto genreDto);

    GenreDto deleteById (Integer id);

    GenreDto updateById (Integer id,GenreDto genreDto);

   // List<GenreDto> findByName ();
}
