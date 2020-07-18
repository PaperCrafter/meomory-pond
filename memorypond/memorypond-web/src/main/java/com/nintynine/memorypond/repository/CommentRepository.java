package com.nintynine.memorypond.repository;

import com.nintynine.memorypond.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByPostId(int postId);

    List<Comment> findAllById(int commentId);

    Comment save(Comment comment);

    void deleteAllByPostId(int postId);
}
