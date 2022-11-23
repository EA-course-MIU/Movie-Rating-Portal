package com.edu.miu.entity;

import com.edu.miu.entity.key.MediaActorKey;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class MediaActor {

    @EmbeddedId
    private MediaActorKey id;

    @ManyToOne
    @JsonBackReference("media-actor")
    @MapsId("mediaId")
    private Media media;

}
