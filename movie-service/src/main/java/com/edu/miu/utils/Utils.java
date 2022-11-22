package com.edu.miu.utils;

import com.edu.miu.dto.MovieDto;
import com.edu.miu.enums.RatingEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    public static void validateRatingDto(MovieDto movieDto) {
        double rating = movieDto.getAverageRating();
        double min = RatingEnum.MIN.getRating();
        double max = RatingEnum.MAX.getRating();

        if (rating < min) {
            movieDto.setAverageRating(min);
        }
        if (rating > RatingEnum.MAX.getRating()) {
            movieDto.setAverageRating(max);
        }
    }

    public static double round2(Double val) {
        return new BigDecimal(val.toString())
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
