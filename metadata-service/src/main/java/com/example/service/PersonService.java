package com.example.service;

import com.example.dto.GenreDto;
import com.example.dto.PersonDto;
import com.example.entity.Person;

import java.util.List;

public interface PersonService {
    List <PersonDto> getAll ();

    PersonDto getById (int id);

    List<PersonDto> getByIds (List<Integer> ids);

    PersonDto addPerson (PersonDto personDto);

    PersonDto deleteById (Integer id);

    PersonDto updateById (Integer id,PersonDto personDto);
//    List<PersonDto> findByTitle (String title);
}
