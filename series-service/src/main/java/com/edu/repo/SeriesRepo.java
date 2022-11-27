package com.edu.repo;

import com.edu.entity.Series;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepo extends CrudRepository<Series, Integer> {
    void deleteAllByOwnerIdIs(String ownerId);
}
