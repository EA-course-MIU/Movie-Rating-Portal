package com.edu.entity;

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
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEDIA_GEN")
    @TableGenerator(
            name = "MEDIA_GEN",
            table = "ID_Generator",
            pkColumnName = "name",
            valueColumnName = "sequence",
            allocationSize = 1)
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

    private String ownerId;
}
