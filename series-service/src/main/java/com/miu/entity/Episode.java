package com.miu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Episode {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private int id;
    private String name;
    private int episodeNumber;
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @Fetch(FetchMode.SELECT)
    private Season season;

    private String ownerId;

    public Episode(String name, int episodeNumber, int duration, Season season, String ownerId) {
        this.name = name;
        this.episodeNumber = episodeNumber;
        this.duration = duration;
        this.season = season;
        this.ownerId = ownerId;
    }
}
