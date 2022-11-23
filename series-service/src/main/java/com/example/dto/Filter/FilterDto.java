package com.example.dto.Filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {
    private Integer year;
    private Double rating;
    private List<Integer> genres;
    private List<Integer> directors;
    private List<Integer> actors;
    private Integer duration;

    public boolean isValidYear() {
        return year != null && year > 0;
    }
    public boolean isValidRating() {
        return rating != null && rating >= 1 && rating <= 5;
    }
    public List<Integer> getPositiveValues(List<Integer> list) {
        if (list == null) {
            return new ArrayList<>();
        }
        return list.stream().filter(id -> id != null && id > 0).toList();
    }
    public List<Integer> getValidGenreIds() {
        return getPositiveValues(genres);
    }
    public List<Integer> getValidDirectorIds() {
        return getPositiveValues(directors);
    }
    public List<Integer> getValidActorIds() {
        return getPositiveValues(actors);
    }
    public boolean isValidDuration() {
        return duration != null && duration > 0;
    }
}
