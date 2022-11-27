package com.edu.repo;

import com.edu.entity.Episode;
import com.edu.entity.Season;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepo extends CrudRepository<Episode, Integer> {
    List<Episode> findAllBySeasonIs(Season season);
    void deleteAllByOwnerIdIs(String ownerId);
}
