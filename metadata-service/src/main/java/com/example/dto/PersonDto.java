package com.example.dto;

import com.example.entity.Position;
import lombok.Data;

import java.util.List;

@Data
public class PersonDto {
    private int id;
    private String name;

    List <Position> positions;
}
