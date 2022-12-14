package com.edu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Season {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private int id;

    private String name;
    private int seasonNumber;
    private int year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Series series;

    @OneToMany(mappedBy = "season",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @Fetch(FetchMode.SUBSELECT)
    private List<Episode> episodes;
    private String ownerId;

    public Season(String name, int seasonNumber, int year, Series series, List<Episode> episodes, String ownerId) {
        this.name = name;
        this.seasonNumber = seasonNumber;
        this.year = year;
        this.series = series;
        this.episodes = episodes;
        this.ownerId = ownerId;
    }
}
