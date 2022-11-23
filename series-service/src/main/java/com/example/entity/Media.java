package com.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private Double rating;

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MediaGenre> genres;

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MediaDirector> directorIds;


    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MediaActor> actorIds;

    private int ownerId;
}
