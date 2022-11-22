package com.edu.miu.dto;

import com.edu.miu.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private int id;

    private String title;

    private String description;

    private double averageRating;

    private List<Integer> genreIds;

    private List<Integer> directorIds;

    private List<Integer> actorIds;

    private int ownerId;

    private int year;

    private String duration;

}
