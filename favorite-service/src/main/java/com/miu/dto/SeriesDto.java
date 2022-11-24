package com.miu.dto;

import com.miu.enums.SeriesStatus;
import lombok.Data;


@Data
public class SeriesDto {
    private int id;
    private String title;
    private String description;
    private double rating;
    private SeriesStatus status;
}
