package com.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Person {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    @JsonManagedReference("person-position")
    List <Position> positions;
}
