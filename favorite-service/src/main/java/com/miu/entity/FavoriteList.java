package com.miu.entity;

import com.miu.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteList {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private int id;

    private String title;
    private String userId;
    @OneToMany(mappedBy = "favoriteList", fetch = javax.persistence.FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
    private List<FavoriteMedia> favoriteMediaList;

    public FavoriteList(String title, String userId) {
        this.title = title;
        this.userId = userId;
        this.favoriteMediaList = new ArrayList<>();
    }
}
