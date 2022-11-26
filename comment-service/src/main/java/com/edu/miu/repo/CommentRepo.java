package com.edu.miu.repo;

import com.edu.miu.entity.Comment;
import com.edu.miu.enums.MediaType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment,Integer> {
    List <Comment> findAllByUserId(String id);
    Comment findByUserId(String id);
    List<Comment> findAllByMediaId(int id);
    List<Comment> findAllByUserIdAndMediaId(String usersId,int mediaId);
    void deleteByUserId(String id);


    @Modifying
    @Query("DELETE FROM Comment c WHERE c.mediaId=:mediaId AND c.mediaType=:mediaType")
    void deleteAllByMediaIdAndMediaType(@Param("mediaId") int mediaId,MediaType mediaType);

    @Modifying
    @Query("DELETE FROM Comment c WHERE c.userId=:userId")
    void deleteAllByUserId (String userId);
}
