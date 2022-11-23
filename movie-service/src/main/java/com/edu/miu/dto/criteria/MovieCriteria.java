package com.edu.miu.dto.criteria;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCriteria {

    private String title;

    private int ownerId;

    private int year;
    private int duration;

    private List<Integer> directors;

    private List<Integer> actors;

    private List<Integer> genres;

}
