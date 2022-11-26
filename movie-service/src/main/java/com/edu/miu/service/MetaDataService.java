package com.edu.miu.service;

import com.edu.miu.dto.GenreDto;
import com.edu.miu.dto.MetaDataDto;
import com.edu.miu.dto.PersonDto;
import com.edu.miu.dto.criteria.MetaDataCriteria;

import java.util.List;

public interface MetaDataService {

    MetaDataDto getMetaDataDto(MetaDataCriteria metaDataCriteria);

    List<GenreDto> getGenres(List<Integer> ids);

    List<PersonDto> getPersons(String position, List<Integer> ids);

}
