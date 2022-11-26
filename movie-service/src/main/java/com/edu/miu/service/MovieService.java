package com.edu.miu.service;

import com.edu.miu.dto.FullMovieDto;
import com.edu.miu.dto.criteria.MovieCriteria;
import com.edu.miu.dto.MovieDto;

import java.util.List;

public interface MovieService {

    FullMovieDto getById(int id);

    List<FullMovieDto> getByDirectorId(int id);

    List<FullMovieDto> getByActorsId(int id);

    List<FullMovieDto> getByGenresId(int id);

    List<FullMovieDto> filterMovies(MovieCriteria movieCriteria);

    List<FullMovieDto> getAll();

    FullMovieDto addMovie(MovieDto movieDto);

    FullMovieDto updateMovie(int id, MovieDto movieDto);

    MovieDto deleteMovie(int id);
    
}
