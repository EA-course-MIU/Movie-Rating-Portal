package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Genre {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
