package com.edu.miu.dao.impl;

import com.edu.miu.dao.MovieDao;
import com.edu.miu.dto.criteria.MovieCriteria;
import com.edu.miu.entity.MediaActor;
import com.edu.miu.entity.MediaDirector;
import com.edu.miu.entity.MediaGenre;
import com.edu.miu.entity.Movie;
import com.edu.miu.entity.key.MediaActorKey;
import com.edu.miu.entity.key.MediaDirectorKey;
import com.edu.miu.entity.key.MediaGenreKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieDaoImpl implements MovieDao {

    private final String LIKE = "%%";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movie> filterMovies(MovieCriteria movieCriteria) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);

        Root<Movie> root= query.from(Movie.class);
        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.isNotBlank(movieCriteria.getTitle())){
            predicates.add(builder.like(root.get("title"), this.addLikeChar(movieCriteria.getTitle())));
        }

        if(movieCriteria.getOwnerId() != null && !movieCriteria.getOwnerId().isEmpty()){
            predicates.add(builder.equal(root.get("ownerId"), movieCriteria.getOwnerId()));
        }

        if(movieCriteria.getYear() > 0){
            predicates.add(builder.equal(root.get("year"), movieCriteria.getYear()));
        }

        if(movieCriteria.getDuration() > 0){
            predicates.add(builder.greaterThanOrEqualTo(root.get("duration"), movieCriteria.getDuration()));
        }

        if(movieCriteria.getDirectors() != null && !movieCriteria.getDirectors().isEmpty()){
            Join<Movie, MediaDirector> directorsJoin = root.join("mediaDirectors");
            CriteriaBuilder.In<Integer> inClause = builder.in(directorsJoin.get("id").get("directorId"));
            for (int id : movieCriteria.getDirectors()) {
                inClause.value(id);
            }
//            movieCriteria.getDirectors().forEach(d -> {
//                predicates1.add(builder.equal(directorsJoin.get("directorId"), d));
//            });

            predicates.add(inClause);

//            predicates.add(builder.or(predicates1.toArray(new Predicate[0])));

//            Subquery<Director> subquery = query.subquery(Director.class);
//            Root<Director> dept = subquery.from(Director.class);
//            subquery.select(dept).distinct(true).where(builder.equal(dept.get("directorId"), id));

//            Subquery<Director> subQuery = query.subquery(Director.class);
//            Root<Director> directorRoot = subQuery.from(Director.class);
//            CriteriaBuilder.In<Integer> inClause = builder.in(directorRoot.get("directorId"));
//            for (int id : movieCriteria.getDirectors()) {
//                inClause.value(id);
//            }
//            subQuery.select(directorRoot).where(directorRoot.get("directorId").in(inClause));
//            predicates.add(builder.equal(root.get("directors"), subQuery));
        }

        if(movieCriteria.getActors() != null && !movieCriteria.getActors().isEmpty()){
            Join<Movie, MediaActor> directorsJoin = root.join("mediaActors");
            CriteriaBuilder.In<Integer> inClause = builder.in(directorsJoin.get("id").get("actorId"));
            for (int id : movieCriteria.getActors()) {
                inClause.value(id);
            }
            predicates.add(inClause);
        }

        if(movieCriteria.getGenres() != null && !movieCriteria.getGenres().isEmpty()){
            Join<Movie, MediaGenre> directorsJoin = root.join("mediaGenres");
            CriteriaBuilder.In<Integer> inClause = builder.in(directorsJoin.get("id").get("genreId"));
            for (int id : movieCriteria.getGenres()) {
                inClause.value(id);
            }
            predicates.add(inClause);
        }

        query.distinct(true).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    private String addLikeChar(String value) {
        return String.format("%s%s%s", LIKE, value, LIKE);
    }
}
