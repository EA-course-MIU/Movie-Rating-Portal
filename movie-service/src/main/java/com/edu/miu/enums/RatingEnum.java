package com.edu.miu.enums;

public enum RatingEnum {
    MIN(0), MAX(5);

    private double rating;

    RatingEnum(double i) {
        this.rating = i;
    }

    public double getRating() {
        return rating;
    }
}
