package com.edu.miu.entity.key;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class MediaGenreKey implements Serializable {

    private int mediaId;

    private int genreId;

}
