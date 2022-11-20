package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;
    private int idProduct;
    private int idUser;
}
