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
public class Movie extends Media {
    private Integer year;

    private Integer duration;

}
