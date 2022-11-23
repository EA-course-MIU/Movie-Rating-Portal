package com.edu.miu.controller;

import com.edu.miu.dto.FullMovieDto;
import com.edu.miu.dto.criteria.MovieCriteria;
import com.edu.miu.dto.MovieDto;
import com.edu.miu.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDto> getAll() {
        return movieService.getAll();
    }

    @GetMapping("/{id}")
    public FullMovieDto getById(@PathVariable int id) {
        return movieService.getById(id);
    }

    @GetMapping("/director/{id}")
    public List<MovieDto> getByDirectorId(@PathVariable int id) {
        return movieService.getByDirectorId(id);
    }

    @GetMapping("/actor/{id}")
    public List<MovieDto> getByActorId(@PathVariable int id) {
        return movieService.getByActorsId(id);
    }

    @GetMapping("/genre/{id}")
    public List<MovieDto> getByGenreId(@PathVariable int id) {
        return movieService.getByGenresId(id);
    }

    @GetMapping("/filter")
    public List<MovieDto> filter(MovieCriteria movieCriteria) {
        return movieService.filterMovies(movieCriteria);
    }

    @PostMapping
    public MovieDto addMovie(@RequestBody MovieDto movieDto) {
        return movieService.addMovie(movieDto);
    }

    @PutMapping("/{id}")
    public MovieDto updateMovie(@PathVariable int id, @RequestBody MovieDto movieDto) {
        return movieService.updateMovie(id, movieDto);
    }

    @DeleteMapping("/{id}")
    public MovieDto deleteMovie(@PathVariable int id) {
        return movieService.deleteMovie(id);
    }
}
