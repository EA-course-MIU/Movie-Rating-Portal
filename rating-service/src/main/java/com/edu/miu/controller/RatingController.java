package com.edu.miu.controller;

import com.edu.miu.dto.RatingDto;
import com.edu.miu.dto.RatingReportDto;
import com.edu.miu.enums.MediaType;
import com.edu.miu.security.AuthHelper;
import com.edu.miu.service.RatingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Rating", description = "Business Rating Services")
public class RatingController {

    private final AuthHelper authHelper;

    private final RatingService ratingService;

    @GetMapping
    public List<RatingDto> getAll() {
        return ratingService.getAll();
    }

    @GetMapping("/media/{id}/report")
    public List<RatingReportDto> getRatingReportToMedia(@PathVariable int id) {
        return ratingService.getRatingReportForMedia(id);
    }

    @GetMapping("/media/{id}/avg")
    public Double getAvgRatingToMedia(@PathVariable int id, @RequestParam MediaType mediaType) {
        return ratingService.getAvgRatingToMedia(id, mediaType);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RatingDto addRating(@RequestBody RatingDto rating) {
        return ratingService.addRating(rating);
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public RatingDto addRatingByUser(@RequestBody RatingDto rating) {
        return ratingService.addRatingByUser(authHelper.getUserId(), rating);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RatingDto updateRating(@PathVariable int id, @RequestBody RatingDto rating) {
        return ratingService.updateRating(id, rating);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public RatingDto updateRatingByUser(@RequestBody RatingDto rating) {
        return ratingService.updateRatingByUser(authHelper.getUserId(), rating);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RatingDto deleteRating(@PathVariable int id) {
        return ratingService.deleteRating(id);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteRatingByMedia(@RequestParam int mediaId, @RequestParam MediaType mediaType) {
        ratingService.deleteRatingByMedia(mediaId, mediaType);
    }

    @DeleteMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public RatingDto deleteRatingByUser(@RequestParam int mediaId, @RequestParam MediaType mediaType) {
        return ratingService.deleteRatingByUser(authHelper.getUserId(), mediaId, mediaType);
    }
}
