package com.edu.miu.utils;

import com.edu.miu.dto.RatingDto;
import com.edu.miu.enums.RatingEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    public static void validateRatingDto(RatingDto ratingDto) {
        int rating = ratingDto.getRating();
        int min = RatingEnum.MIN.getRating();
        int max = RatingEnum.MAX.getRating();

        if (rating < min) {
            ratingDto.setRating(min);
        }
        if (rating > RatingEnum.MAX.getRating()) {
            ratingDto.setRating(max);
        }
    }

    public static double round2(Double val) {
        return new BigDecimal(val.toString())
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
