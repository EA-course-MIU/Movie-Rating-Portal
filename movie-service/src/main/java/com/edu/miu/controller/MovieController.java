package com.edu.miu.controller;

import com.edu.miu.dto.FullMovieDto;
import com.edu.miu.dto.criteria.MovieCriteria;
import com.edu.miu.dto.MovieDto;
import com.edu.miu.service.MovieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Tag(name = "Movie", description = "Business Movie Services")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<FullMovieDto> getAll() {
        return movieService.getAll();
    }

    @GetMapping("/{id}")
    public FullMovieDto getById(@PathVariable int id) {
        return movieService.getById(id);
    }

    @GetMapping("/director/{id}")
    public List<FullMovieDto> getByDirectorId(@PathVariable int id) {
        return movieService.getByDirectorId(id);
    }

    @GetMapping("/actor/{id}")
    public List<FullMovieDto> getByActorId(@PathVariable int id) {
        return movieService.getByActorsId(id);
    }

    @GetMapping("/genre/{id}")
    public List<FullMovieDto> getByGenreId(@PathVariable int id) {
        return movieService.getByGenresId(id);
    }

    @GetMapping("/filter")
    public List<FullMovieDto> filter(MovieCriteria movieCriteria) {
        return movieService.filterMovies(movieCriteria);
    }

    @PostMapping
    public FullMovieDto addMovie(@RequestBody MovieDto movieDto) {
        return movieService.addMovie(movieDto);
    }

    @PutMapping("/{id}")
    public FullMovieDto updateMovie(@PathVariable int id, @RequestBody MovieDto movieDto) {
        return movieService.updateMovie(id, movieDto);
    }

    @DeleteMapping("/{id}")
    public MovieDto deleteMovie(@PathVariable int id) {
        return movieService.deleteMovie(id);
    }
}
