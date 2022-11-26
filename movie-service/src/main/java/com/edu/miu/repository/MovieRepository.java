package com.edu.miu.repository;

import com.edu.miu.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

    List<Movie> findAll();

    List<Movie> findAllByMediaDirectorsIdDirectorId(int id);

    List<Movie> findAllByMediaActorsIdActorId(int id);

    List<Movie> findAllByMediaGenresIdGenreId(int id);

}
