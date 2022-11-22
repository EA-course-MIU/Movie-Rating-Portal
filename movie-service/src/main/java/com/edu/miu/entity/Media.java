package com.edu.miu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String title;
    private String description;
    private double averageRating;

    private int ownerId;

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonManagedReference("media-genre")
    private List<Genre>  genres;

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonManagedReference("media-director")
    private List<Director> directors;

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonManagedReference("media-actor")
    private List<Actor> actors;

}



