package com.example.service.Impl;

import com.example.dto.GenreDto;
import com.example.dto.PersonDto;
import com.example.entity.Genre;
import com.example.mapper.GenreMapper;
import com.example.mapper.PersonMapper;
import com.example.repo.GenreRepo;
import com.example.service.PersonService;
import com.example.entity.Person;
import com.example.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepo personRepo;

    private final PersonMapper personMapper;
    @Override
    public List <PersonDto> getAll () {
        return personMapper.toDtos ((List<Person>) personRepo.findAll());
    }

    @Override
    public PersonDto getById (int id) {
        return personMapper.toDto (personRepo.findById (id).get ());
    }

    @Override
    @Transactional
    public PersonDto addPerson (PersonDto personDto) {
        Person person = personMapper.toEntity (personDto);
        personRepo.save (person);

        return personMapper.toDto (person);
    }

    @Override
    @Transactional
    public PersonDto deleteById (Integer id) {
        Person person = personRepo.findById (id).get ();
            personRepo.deleteById (id);

        return personMapper.toDto (person);
    }
    @Override
    @Transactional
    public PersonDto updateById (Integer id,PersonDto personDto) {
        Person person = personMapper.toEntity (personDto);
        if(person != null){
            personRepo.save (person);
        }
        return personMapper.toDto (person);
    }

//    @Override
//    public List <PersonDto> findByName () {
//        return personMapper.toDtos (personRepo ());
//    }
}
