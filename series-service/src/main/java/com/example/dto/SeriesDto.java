package com.example.dto;

import com.example.constant.SeriesStatus;
import com.example.entity.MediaDirector;
import com.example.entity.MediaGenre;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import java.util.List;

@Data
public class SeriesDto {
    private int id;
    private String title;
    private String description;
    private double rating;
    private List<PersonDto> directors;
    private List<PersonDto> actors;
    private List<GenreDto> genres;
    private SeriesStatus status;
}
