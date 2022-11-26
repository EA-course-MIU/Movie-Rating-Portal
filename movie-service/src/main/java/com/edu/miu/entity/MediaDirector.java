package com.edu.miu.entity;

import com.edu.miu.entity.key.MediaDirectorKey;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class MediaDirector {

    @EmbeddedId
    private MediaDirectorKey id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference("media-director")
    @MapsId("mediaId")
    private Media media;

}
