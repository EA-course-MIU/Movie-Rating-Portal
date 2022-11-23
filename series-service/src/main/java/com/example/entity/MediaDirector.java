package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MediaDirector {
    @EmbeddedId
    private MediaPersonKey mediaPersonKey;
    @ManyToOne
    @JsonBackReference
    @MapsId("mediaId")
    private Media media;
}
