package com.example.controller;

import com.example.dto.GenreDto;
import com.example.dto.PersonDto;
import com.example.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<PersonDto> getAll(@RequestParam(value = "ids", required = false) List<Integer> ids) {
        if (ids != null && ids.size() > 0) {
            return personService.getByIds(ids);
        }
        return personService.getAll();
    }

    @GetMapping(path = "/filter")
    public List<PersonDto> filterPersons(@RequestParam(value="position", required = false) String position,
                                            @RequestParam(value = "ids", required = false) List<Integer> ids) {
        if (ids != null && ids.size() > 0) {
            if (StringUtils.isNotBlank(position)) {
                return personService.getByIdsAndType(ids, position);
            }
            return personService.getByIds(ids);
        } else if (StringUtils.isNotBlank(position)) {
            return personService.getByPosition(position);
        }
        return Collections.EMPTY_LIST;
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
//    @GetMapping("/filter")
//    public List <GenreDto> findByName(@RequestParam String title){
//        return personService.findByTitle (title);
//    }

}
