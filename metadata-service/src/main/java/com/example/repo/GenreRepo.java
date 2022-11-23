package com.example.repo;

import com.example.entity.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepo extends CrudRepository<Genre,Integer> {
    @Query("SELECT g FROM Genre g WHERE g.name = ?1")
    List<Genre> findAllByName(String name);

}
