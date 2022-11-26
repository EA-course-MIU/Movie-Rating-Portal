package com.example.mapper;
import com.example.dto.PersonDto;
import com.example.entity.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonMapper {
    private final ModelMapper modelMapper;

    public PersonDto toDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    public List <PersonDto> toDtos(List<Person> persons) {
        return persons.stream()
                .map(this::toDto)
                .toList();
    }

    public Person toEntity(PersonDto personDto) {
        return modelMapper.map(personDto,Person.class);
    }

    public List<Person> toEntities(List<PersonDto> personDtos) {
        return personDtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
