package com.edu.miu.dao;

import com.edu.miu.dto.criteria.MovieCriteria;
import com.edu.miu.entity.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> filterMovies(MovieCriteria movieCriteria);

}
