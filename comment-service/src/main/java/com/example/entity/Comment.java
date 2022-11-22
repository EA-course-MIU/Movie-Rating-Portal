package com.example.entity;

import com.example.enums.MediaType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createdDate;
    private Date updateDate;
    private String comment;

    private int userId;
    private int mediaId;
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

}
