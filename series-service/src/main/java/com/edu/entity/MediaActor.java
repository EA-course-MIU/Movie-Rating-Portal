package com.edu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Data
@Entity
public class MediaActor {
    @EmbeddedId
    private MediaPersonKey mediaPersonKey;
    @ManyToOne
    @JsonBackReference
    @MapsId("mediaId")
    private Media media;
}
