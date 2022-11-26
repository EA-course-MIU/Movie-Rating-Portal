package com.miu.repo;

import com.miu.entity.Season;
import com.miu.entity.Series;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepo extends CrudRepository<Season, Integer> {
    List<Season> findAllBySeriesIs(Series series);
    void deleteAllByOwnerIdIs(String ownerId);
}
