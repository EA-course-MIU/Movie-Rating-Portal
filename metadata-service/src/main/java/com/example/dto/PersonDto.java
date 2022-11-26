package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonDto {
    private int id;
    private String name;

    List <PositionDto> positions;

}
