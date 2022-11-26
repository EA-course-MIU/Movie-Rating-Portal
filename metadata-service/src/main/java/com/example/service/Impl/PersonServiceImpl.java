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
import java.util.Objects;

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
    public List<PersonDto> getByIds(List<Integer> ids) {
        return personMapper.toDtos((List<Person>) personRepo.findAllById(ids));
    }

    @Override
    public List<PersonDto> getByIdsAndType(List<Integer> ids, String position) {
        return personMapper.toDtos(personRepo.findAllByIdIsInAndPositionsTitleEqualsIgnoreCase(ids, position));
    }

    @Override
    public List<PersonDto> getByPosition(String position) {
        return personMapper.toDtos(personRepo.findAllByPositionsTitleEqualsIgnoreCase(position));
    }

    @Override
    @Transactional
    public PersonDto addPerson (PersonDto personDto) {
        Person person = personMapper.toEntity (personDto);
        if(personRepo.findById (person.getId ()).isPresent ()){
            throw new RuntimeException ("person id :"+person.getId () + "is existing");
        }
        else {
            personRepo.save (person);
        }

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
        Person exist = personRepo.findById (id) .orElseThrow (()->new IllegalStateException (
                "Person id:"+id+"does not exist"));;

        if(exist.getName () !=null &&exist.getName ().length ()>0 && !Objects.equals (exist.getName (),person.getName ())){
            exist.setName (person.getName ());
        }

        return personMapper.toDto (exist);
    }

//    @Override
//    public List <PersonDto> findByTitle (String title) {
//        return personMapper.toDtos (personRepo.findByTitle (title));
//    }
}
