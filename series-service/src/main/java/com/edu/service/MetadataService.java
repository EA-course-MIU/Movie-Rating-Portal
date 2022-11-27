package com.edu.service;

import com.edu.dto.GenreDto;
import com.edu.dto.PersonDto;

public interface MetadataService {
    PersonDto getPersonById(int id);
    GenreDto getGenreById(int id);
}
