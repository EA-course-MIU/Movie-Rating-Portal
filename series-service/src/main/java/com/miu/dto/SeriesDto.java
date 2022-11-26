package com.miu.dto;

import com.miu.constant.SeriesStatus;
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
