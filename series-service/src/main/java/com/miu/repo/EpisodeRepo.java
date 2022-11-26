package com.miu.repo;

import com.miu.entity.Episode;
import com.miu.entity.Season;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepo extends CrudRepository<Episode, Integer> {
    List<Episode> findAllBySeasonIs(Season season);
    void deleteAllByOwnerIdIs(String ownerId);
}
