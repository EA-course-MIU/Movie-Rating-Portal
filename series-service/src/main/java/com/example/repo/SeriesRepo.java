package com.example.repo;

import com.example.entity.Series;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepo extends CrudRepository<Series, Integer> {
}
