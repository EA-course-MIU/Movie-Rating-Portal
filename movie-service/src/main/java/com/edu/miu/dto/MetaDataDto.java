package com.edu.miu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaDataDto {

    private List<GenreDto> genres = new ArrayList<>();

    private List<PersonDto> directors = new ArrayList<>();

    private List<PersonDto> actors = new ArrayList<>();
}
