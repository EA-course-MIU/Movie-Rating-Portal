package com.example.controller;

import com.example.dto.PersonDto;
import com.example.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List <PersonDto> getAll(){
        return personService.getAll ();

    }
    @GetMapping(path = "/{id}")
    public PersonDto getById(@PathVariable("id") Integer id){
        return personService.getById (id);
    }

    @PostMapping
    public PersonDto addPerson(@RequestBody PersonDto personDto){
        return personService.addPerson (personDto);
    }

    @DeleteMapping(path = "/{id}")
    public PersonDto DeleteById(@PathVariable("id") Integer id){
         return personService.deleteById (id);
    }

    @PutMapping(path = "/{id}")
    public PersonDto UpdateById(@PathVariable("id") Integer id,@RequestBody PersonDto personDto){
         return personService.updateById (id,personDto);
    }



}