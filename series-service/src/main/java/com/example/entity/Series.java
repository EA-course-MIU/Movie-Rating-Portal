package com.example.entity;

import com.example.constant.SeriesStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Series extends Media{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEDIA_GEN")
    @TableGenerator(
            name = "MEDIA_GEN",
            table = "ID_Generator",
            pkColumnName = "name",
            valueColumnName = "sequence",
            allocationSize = 1)
    private int id;
    @Enumerated(EnumType.STRING)
    private SeriesStatus status = SeriesStatus.ONGOING;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Season> seasons;
}
