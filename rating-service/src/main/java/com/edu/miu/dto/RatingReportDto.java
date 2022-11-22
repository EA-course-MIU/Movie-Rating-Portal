package com.edu.miu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingReportDto {

    private int rating;

    private int votes;

    private double percent;

}
