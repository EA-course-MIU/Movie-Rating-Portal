package com.miu.service.impl;

import com.miu.dto.GenreDto;
import com.miu.dto.PersonDto;

public interface MetadataService {
    PersonDto getPersonById(int id);
    GenreDto getGenreById(int id);
}
