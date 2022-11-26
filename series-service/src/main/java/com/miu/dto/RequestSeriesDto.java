package com.miu.dto;

import com.miu.constant.SeriesStatus;
import lombok.Data;

@Data
public class RequestSeriesDto {
    private String title;
    private String description;
    private SeriesStatus status;
}
