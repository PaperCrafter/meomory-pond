package com.nintynine.memorypond.repository;

import com.nintynine.memorypond.model.Comment;
import com.nintynine.memorypond.model.projection.CommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query()
    List<Comment> findAllByPostId(int postId);

    List<Comment> findAllById(int commentId);

    Comment save(Comment comment);

    void deleteAllByPostId(int postId);
}
