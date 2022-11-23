package com.edu.miu.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Media implements Serializable {

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
    private double averageRating;

    private int ownerId;

    @ColumnDefault("false")
    private boolean isDisabled = false;

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonManagedReference("media-genre")
    private List<MediaGenre> mediaGenres;

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonManagedReference("media-director")
    private List<MediaDirector> mediaDirectors;

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonManagedReference("media-actor")
    private List<MediaActor> mediaActors;

}



