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
    private List<MediaDirector> directorIds;
    private List<MediaDirector> actorIds;
    private List<MediaGenre> genres;
    private SeriesStatus status;
}
