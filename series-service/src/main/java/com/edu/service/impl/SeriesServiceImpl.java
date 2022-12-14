package com.edu.service.impl;

import com.edu.entity.Series;
import com.edu.exception.BadRequestException;
import com.edu.exception.ResourceNotFoundException;
import com.edu.repo.SeriesRepo;
import com.edu.service.SeriesService;
import com.edu.dto.Filter.FilterDto;
import com.edu.dto.RequestSeriesDto;
import com.edu.dto.SeriesDto;
import com.edu.entity.Episode;
import com.edu.entity.MediaGenre;
import com.edu.entity.Season;
import com.edu.mapper.SeriesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepo seriesRepo;

    private final SeriesMapper seriesMapper;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public SeriesDto getById(int id) {
        var series = seriesRepo.findById(id).orElseThrow(() -> new RuntimeException("Series not found"));
        return seriesMapper.toDto(series);
    }

    @Override
    public List<SeriesDto> getAll() {
        return seriesMapper.toListDto(seriesRepo.findAll());
    }


    @Transactional
    @Override
    public void deleteById(int id, String userId) {
        Series series = seriesRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Series not found"));
        if (!series.getOwnerId().equals(userId)) throw new BadRequestException("You cannot delete this series");
        seriesRepo.deleteById(id);
    }


    @Transactional
    @Override
    public SeriesDto updateSeries(int id, RequestSeriesDto series, String userId) {
        Series updatingSeries = seriesRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Series not found"));

        if (!updatingSeries.getOwnerId().equals(userId)) {
            throw new BadRequestException("You cannot update this series");
        }
        if (series.getTitle() != null) {
            updatingSeries.setTitle(series.getTitle());
        }
        if (series.getDescription() != null) {
            updatingSeries.setDescription(series.getDescription());
        }
        if (series.getStatus() != null) {
            updatingSeries.setStatus(series.getStatus());
        }
        return seriesMapper.toDto(updatingSeries);
    }


    @Transactional
    @Override
    public SeriesDto saveSeries(RequestSeriesDto series, String userId) {
        System.out.println("===="+ userId);
        Series newSeries = new Series();
        newSeries.setTitle(series.getTitle());
        newSeries.setDescription(series.getDescription());
        newSeries.setStatus(series.getStatus());
        newSeries.setOwnerId(userId);
        newSeries.setGenres(new ArrayList<>());
        newSeries.setActorIds(new ArrayList<>());
        newSeries.setDirectorIds(new ArrayList<>());
        newSeries.setGenres(new ArrayList<>());
        seriesRepo.save(newSeries);
        return seriesMapper.toDto(newSeries);
    }

    @Override
    public List<SeriesDto> filter(FilterDto filterDto) {
        if (!filterDto.hasFilter()) {
            return new ArrayList<>();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Series> query = criteriaBuilder.createQuery(Series.class);

        Metamodel metamodel = entityManager.getMetamodel();
        EntityType<Series> Series_ = metamodel.entity(Series.class);
        EntityType<Season> Season_ = metamodel.entity(Season.class);
        EntityType<Episode> Episode_ = metamodel.entity(Episode.class);

        Root<Series> root = query.from(Series.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filterDto.isValidYear()) {
            Join<Series, Season> seasonJoin = root.join("seasons");
            CriteriaBuilder.In<Integer> inClause = criteriaBuilder.in(seasonJoin.get("year"));
            inClause.value(filterDto.getYear());
            predicates.add(inClause);
        }

        if (filterDto.isValidRating()) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Series_.getSingularAttribute("rating", Double.class)), filterDto.getRating()));
        }

        List<Integer> genreIds = filterDto.getValidGenreIds();
        if (!genreIds.isEmpty()) {
            Join<Series, MediaGenre> genreJoin = root.join("genres");
            CriteriaBuilder.In<Integer> inClause = criteriaBuilder.in(genreJoin.get("mediaGenreKey").get("genreId"));
            for (int id : genreIds) {
                inClause.value(id);
            }
            predicates.add(inClause);
        }

        List<Integer> directorIds = filterDto.getValidDirectorIds();
        if (!directorIds.isEmpty()) {
            Join<Series, MediaGenre> directorJoin = root.join("directorIds");
            CriteriaBuilder.In<Integer> inClause = criteriaBuilder.in(directorJoin.get("mediaPersonKey").get("personId"));
            for (int id : directorIds) {
                inClause.value(id);
            }
            predicates.add(inClause);
        }

        List<Integer> actorIds = filterDto.getValidActorIds();
        if (!actorIds.isEmpty()) {
            Join<Series, MediaGenre> actorJoin = root.join("actorIds");
            CriteriaBuilder.In<Integer> inClause = criteriaBuilder.in(actorJoin.get("mediaPersonKey").get("personId"));
            for (int id : actorIds) {
                inClause.value(id);
            }
            predicates.add(inClause);
        }

        query.distinct(true).where(predicates.toArray(new Predicate[0]));

        return seriesMapper.toListDto(entityManager.createQuery(query).getResultList());
    }

    @Transactional
    @Override
    public SeriesDto updateRating(int id, double rating) {
        var series = seriesRepo.findById(id).orElse(null);
        if (series == null) {
            return null;
        }
        series.setRating(rating);
        return seriesMapper.toDto(series);
    }
}
