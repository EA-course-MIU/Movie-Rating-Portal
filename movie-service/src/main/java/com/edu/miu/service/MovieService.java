package com.edu.miu.service;

import com.edu.miu.dto.FullMovieDto;
import com.edu.miu.dto.criteria.MovieCriteria;
import com.edu.miu.dto.MovieDto;

import java.util.List;

public interface MovieService {

    FullMovieDto getById(int id);

    List<MovieDto> getByDirectorId(int id);

    List<MovieDto> getByActorsId(int id);

    List<MovieDto> getByGenresId(int id);

    List<MovieDto> filterMovies(MovieCriteria movieCriteria);

    List<MovieDto> getAll();

    MovieDto addMovie(MovieDto movieDto);

    MovieDto updateMovie(int id, MovieDto movieDto);

    MovieDto deleteMovie(int id);
    
}
