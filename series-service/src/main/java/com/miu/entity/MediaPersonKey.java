package com.miu.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class MediaPersonKey implements Serializable {
    private int mediaId;
    private int personId;
}
