package com.miu.dto;

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

    private int year;

    private int duration;

}
