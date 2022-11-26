package com.example.controller;

import com.example.dto.GenreDto;
import com.example.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List <GenreDto> getAll(@RequestParam(value = "ids", required = false) List<Integer> ids){
        if (ids != null && ids.size() > 0) {
            return genreService.getByIds(ids);
        }
        return genreService.getAll ();

    }
    @GetMapping(path = "/{id}")
    public GenreDto getById(@PathVariable("id") Integer id){
        return genreService.getById (id);

    }

    @PostMapping
    public GenreDto addGenre(@RequestBody GenreDto genreDto){
        return genreService.addGenre (genreDto);
    }

    @DeleteMapping(path = "/{id}")
    public GenreDto DeleteById(@PathVariable("id") Integer id){
        return genreService.deleteById (id);
    }

    @PutMapping(path = "/{id}")
    public GenreDto UpdateById(@PathVariable("id") Integer id,@RequestBody GenreDto genreDto){
       return genreService.updateById (id,genreDto);
    }

    @GetMapping("/filter")
    public List <GenreDto> findByName(@RequestParam String name){
        return genreService.findByName (name);
    }

}
