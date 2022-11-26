package com.miu.repo;

import com.miu.entity.FavoriteList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteListRepo extends CrudRepository<FavoriteList, Integer> {
    List<FavoriteList> findAllByUserIdIs(String userId);
    void deleteAllByUserIdIs(String userId);

}
