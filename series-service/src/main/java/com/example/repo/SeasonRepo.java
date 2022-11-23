package com.example.repo;

import com.example.entity.Season;
import com.example.entity.Series;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepo extends CrudRepository<Season, Integer> {
    List<Season> findAllBySeriesIs(Series series);
}
