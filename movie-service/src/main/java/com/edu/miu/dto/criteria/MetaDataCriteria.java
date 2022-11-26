package com.edu.miu.dto.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaDataCriteria {

    private List<Integer> genreIds;

    private List<Integer> directorIds;

    private List<Integer> actorIds;

}
