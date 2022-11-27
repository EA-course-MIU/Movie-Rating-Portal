package com.edu.dto;

import com.edu.constant.SeriesStatus;
import lombok.Data;

@Data
public class RequestSeriesDto {
    private String title;
    private String description;
    private SeriesStatus status;
}
