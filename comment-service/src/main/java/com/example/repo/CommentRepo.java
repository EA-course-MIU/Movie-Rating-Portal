package com.example.repo;

import com.example.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment,Integer> {
    List <Comment> findAllByUserId(int id);
    Comment findByUserId(int id);
    List<Comment> findAllByMediaId(int id);
    List<Comment> findAllByUserIdAndMediaId(int usersId,int mediaId);
    void deleteByUserId(int id);



}
