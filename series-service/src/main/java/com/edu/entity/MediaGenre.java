package com.edu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Data
@Entity
public class MediaGenre {
    @EmbeddedId
    private MediaGenreKey mediaGenreKey;

    @ManyToOne
    @MapsId("mediaId")
    @JsonBackReference
    private Media media;
}
