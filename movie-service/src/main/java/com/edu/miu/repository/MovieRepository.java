package com.edu.miu.repository;

import com.edu.miu.dto.MovieDto;
import com.edu.miu.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

    List<Movie> findAll();

    List<Movie> findAllByDirectorsId(int id);

    List<Movie> findAllByActorsId(int id);

    List<Movie> findAllByGenresId(int id);

}
