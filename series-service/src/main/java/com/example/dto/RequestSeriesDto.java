package com.example.dto;

import com.example.constant.SeriesStatus;
import lombok.Data;

@Data
public class RequestSeriesDto {
    private String title;
    private String description;
    private SeriesStatus status;
}
