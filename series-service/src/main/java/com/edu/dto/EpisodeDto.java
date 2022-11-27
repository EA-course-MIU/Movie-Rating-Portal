package com.edu.dto;

import lombok.Data;

@Data
public class EpisodeDto {
    private int id;
    private String name;
    private int episodeNumber;
    private int duration;
    private int seasonId;
}
