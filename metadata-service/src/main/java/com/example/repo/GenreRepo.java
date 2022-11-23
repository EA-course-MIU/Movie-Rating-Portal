package com.example.repo;

import com.example.entity.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepo extends CrudRepository<Genre,Integer> {


}
