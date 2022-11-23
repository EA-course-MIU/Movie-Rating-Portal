package com.example.repo;

import com.example.entity.Episode;
import com.example.entity.Season;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepo extends CrudRepository<Episode, Integer> {
    List<Episode> findAllBySeasonIs(Season season);
}
