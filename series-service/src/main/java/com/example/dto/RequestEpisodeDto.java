package com.example.dto;

import lombok.Data;

@Data
public class RequestEpisodeDto {
    private String name;
    private Integer episodeNumber;
    private Integer duration;
}
