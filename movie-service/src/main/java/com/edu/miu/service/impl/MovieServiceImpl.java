package com.edu.miu.service.impl;

import com.edu.miu.dao.MovieDao;
import com.edu.miu.dto.RatingDto;
import com.edu.miu.dto.criteria.MovieCriteria;
import com.edu.miu.dto.MovieDto;
import com.edu.miu.entity.MediaActor;
import com.edu.miu.entity.MediaDirector;
import com.edu.miu.entity.MediaGenre;
import com.edu.miu.entity.Movie;
import com.edu.miu.entity.key.MediaActorKey;
import com.edu.miu.entity.key.MediaDirectorKey;
import com.edu.miu.entity.key.MediaGenreKey;
import com.edu.miu.dao.repository.MovieRepository;
import com.edu.miu.enums.MediaType;
import com.edu.miu.publisher.MoviePublisher;
import com.edu.miu.publisher.RabbitMQService;
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

    private final MoviePublisher moviePublisher;

    private final RabbitMQService rabbitMQService;

    @Override
    public MovieDto getById(int id) {
        return this.convertTo(this.getMovieById(id));
    }

    @Override
    public List<MovieDto> getByDirectorId(int id) {
        return movieRepository.findAllByMediaDirectorsIdDirectorId(id).stream()
                .map(this::convertTo).toList();
    }

    @Override
    public List<MovieDto> getByActorsId(int id) {
        return movieRepository.findAllByMediaActorsIdActorId(id).stream()
                .map(this::convertTo).toList();
    }

    @Override
    public List<MovieDto> getByGenresId(int id) {
        return movieRepository.findAllByMediaGenresIdGenreId(id).stream()
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
            if (movieDto.getDescription() != null && !movie.getDescription().equals(movieDto.getDescription())) {
                movie.setDescription(movieDto.getDescription());
            }
            if (movieDto.getTitle() != null && !movie.getTitle().equals(movieDto.getTitle())) {
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
            if (movieDto.getDuration() > 0 && !movie.getDuration().equals(movieDto.getDuration())) {
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
            RatingDto ratingDto = new RatingDto();
            ratingDto.setMediaId(id);
            ratingDto.setMediaType(MediaType.MOVIE);

            moviePublisher.sendMessageToRemoveRating(ratingDto);

//            rabbitMQService.sendExchange("movie-topic-exchange", "remove-rating-queue", ratingDto);
        }

        return this.convertTo(movie);
    }

    private MovieDto convertTo(Movie movie) {
        if (movie == null) {
            return null;
        }
        MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
        if (movie.getMediaDirectors() != null) {
            movieDto.setDirectorIds(movie.getMediaDirectors().stream().map(m -> m.getId().getDirectorId()).toList());
        }
        if (movie.getMediaActors() != null) {
            movieDto.setActorIds(movie.getMediaActors().stream().map(m -> m.getId().getActorId()).toList());
        }
        if (movie.getMediaGenres() != null) {
            movieDto.setGenreIds(movie.getMediaGenres().stream().map(m -> m.getId().getGenreId()).toList());
        }
        return movieDto;
    }

    private Movie convertTo(MovieDto movieDto) {
        if (movieDto == null) {
            return null;
        }
        Utils.validateRatingDto(movieDto);

        Movie movie = modelMapper.map(movieDto, Movie.class);

        if (movieDto.getDirectorIds() != null) {
            List<MediaDirector> mediaDirectors = movieDto.getDirectorIds().stream().map(id -> {
                MediaDirectorKey mediaDirectorKey = new MediaDirectorKey();
                mediaDirectorKey.setDirectorId(id);
                return new MediaDirector(mediaDirectorKey, movie);
            }).toList();
            movie.setMediaDirectors(mediaDirectors);
        }
        if (movieDto.getActorIds() != null) {
            List<MediaActor> mediaActors = movieDto.getActorIds().stream().map(id -> {
                MediaActorKey mediaActorKey = new MediaActorKey();
                mediaActorKey.setActorId(id);
                return new MediaActor(mediaActorKey, movie);
            }).toList();
            movie.setMediaActors(mediaActors);
        }
        if (movieDto.getGenreIds() != null) {
            List<MediaGenre> mediaGenres = movieDto.getActorIds().stream().map(id -> {
                MediaGenreKey mediaGenreKey = new MediaGenreKey();
                mediaGenreKey.setGenreId(id);
                return new MediaGenre(mediaGenreKey, movie);
            }).toList();
            movie.setMediaGenres(mediaGenres);
        }
        return movie;
    }

}
