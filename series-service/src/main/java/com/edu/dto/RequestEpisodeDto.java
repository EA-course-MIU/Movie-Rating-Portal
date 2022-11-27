package com.edu.dto;

import lombok.Data;

@Data
public class RequestEpisodeDto {
    private String name;
    private Integer episodeNumber;
    private Integer duration;
}
