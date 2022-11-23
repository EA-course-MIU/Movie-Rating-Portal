package com.example.repo;

import com.example.entity.Genre;
import com.example.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends CrudRepository<Person,Integer> {
//    @Query("SELECT p FROM Person p WHERE p.positions = ?1")
//    List<Genre> findAllByPositions();


}
