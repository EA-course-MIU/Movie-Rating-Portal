package com.edu.miu.service.impl;

import com.edu.miu.dao.MovieDao;
import com.edu.miu.dto.criteria.MovieCriteria;
import com.edu.miu.dto.MovieDto;
import com.edu.miu.entity.Actor;
import com.edu.miu.entity.Director;
import com.edu.miu.entity.Genre;
import com.edu.miu.entity.Movie;
import com.edu.miu.repository.MovieRepository;
import com.edu.miu.service.MovieService;
import com.edu.miu.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieDao movieDao;

    private final ModelMapper modelMapper;

    @Override
    public MovieDto getById(int id) {
        return this.convertTo(this.getMovieById(id));
    }

    @Override
    public List<MovieDto> getByDirectorId(int id) {
        return movieRepository.findAllByDirectorsId(id).stream()
                .map(this::convertTo).toList();
    }

    @Override
    public List<MovieDto> getByActorsId(int id) {
        return movieRepository.findAllByActorsId(id).stream()
                .map(this::convertTo).toList();
    }

    @Override
    public List<MovieDto> getByGenresId(int id) {
        return movieRepository.findAllByGenresId(id).stream()
                .map(this::convertTo).toList();
    }

    @Override
    public List<MovieDto> filterMovies(MovieCriteria movieCriteria) {
        return movieDao.filterMovies(movieCriteria).stream()
                .map(this::convertTo)
                .toList();
    }

    private Movie getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public List<MovieDto> getAll() {
        return movieRepository.findAll().stream()
                .map(this::convertTo)
                .toList();
    }

    @Override
    @Transactional
    public MovieDto addMovie(MovieDto movieDto) {
        return this.convertTo(movieRepository.save(this.convertTo((movieDto))));
    }

    @Override
    @Transactional
    public MovieDto updateMovie(int id, MovieDto movieDto) {
        Movie movie = this.getMovieById(id);

        if (movie != null) {
            if (movieDto.getDescription() != null && movie.getDescription().equals(movieDto.getDescription())) {
                movie.setDescription(movieDto.getDescription());
            }
            if (movieDto.getTitle() != null && movie.getTitle().equals(movieDto.getTitle())) {
                movie.setTitle(movieDto.getTitle());
            }
            if (movieDto.getAverageRating() > 0 && movie.getAverageRating() != movieDto.getAverageRating()) {
                movie.setAverageRating(movieDto.getAverageRating());
            }
            if (movieDto.getOwnerId() > 0 && movie.getOwnerId() != movieDto.getOwnerId()) {
                movie.setOwnerId(movieDto.getOwnerId());
            }
            if (movieDto.getYear() > 1900 && movie.getYear() != movieDto.getYear()) {
                movie.setYear(movieDto.getYear());
            }
            if (movieDto.getDuration() != null && movie.getDuration().equals(movieDto.getDuration())) {
                movie.setDuration(movieDto.getDuration());
            }
        }

        return this.convertTo(movieRepository.save(movie));
    }

    @Override
    @Transactional
    public MovieDto deleteMovie(int id) {
        Movie movie = this.getMovieById(id);

        if (movie != null) {
            movieRepository.deleteById(id);
        }

        return this.convertTo(movie);
    }

    private MovieDto convertTo(Movie movie) {
        if (movie == null) {
            return null;
        }
        MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
        movieDto.setDirectorIds(movie.getDirectors().stream().map(Director::getDirectorId).toList());
        movieDto.setActorIds(movie.getActors().stream().map(Actor::getActorId).toList());
        movieDto.setGenreIds(movie.getGenres().stream().map(Genre::getGenreId).toList());
        return movieDto;
    }

    private Movie convertTo(MovieDto movieDto) {
        if (movieDto == null) {
            return null;
        }
        Utils.validateRatingDto(movieDto);
        return modelMapper.map(movieDto, Movie.class);
    }

}
