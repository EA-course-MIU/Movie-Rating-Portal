package com.edu.miu.entity;

import com.edu.miu.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int rating;

    private int userId;

    private int mediaId;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

}
