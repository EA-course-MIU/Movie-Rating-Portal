package com.edu.miu.entity;

import com.edu.miu.entity.key.MediaGenreKey;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class MediaGenre {

    @EmbeddedId
    private MediaGenreKey id;

    @ManyToOne
    @JsonBackReference("media-genre")
    @MapsId("mediaId")
    private Media media;

}