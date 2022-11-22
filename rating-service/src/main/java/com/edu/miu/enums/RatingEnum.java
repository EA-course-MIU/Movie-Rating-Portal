package com.edu.miu.enums;

public enum RatingEnum {
    MIN(0), MAX(5);

    private int rating;

    RatingEnum(int i) {
        this.rating = i;
    }

    public int getRating() {
        return rating;
    }
}
