package com.edu.miu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullMovieDto {

    private int id;

    private String title;

    private String description;

    private double averageRating;

    private List<GenreDto> genres;

    private List<PersonDto> directors;

    private List<PersonDto> actors;

    private int ownerId;

    private int year;

    private int duration;

}
