package com.edu.miu.controller;

import com.edu.miu.dto.RatingDto;
import com.edu.miu.dto.RatingReportDto;
import com.edu.miu.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping
    public List<RatingDto> getAll() {
        return ratingService.getAll();
    }

    @GetMapping("/media/{id}/report")
    public List<RatingReportDto> getRatingReportToMedia(@PathVariable int id) {
        return ratingService.getRatingReportForMedia(id);
    }

    @PostMapping
    public RatingDto addRating(@RequestBody RatingDto rating) {
        return ratingService.addRating(rating);
    }

    @PutMapping("/{id}")
    public RatingDto updateRating(@PathVariable int id, @RequestBody RatingDto rating) {
        return ratingService.updateRating(id, rating);
    }

    @DeleteMapping("/{id}")
    public RatingDto deleteRating(@PathVariable int id) {
        return ratingService.deleteRating(id);
    }
}
