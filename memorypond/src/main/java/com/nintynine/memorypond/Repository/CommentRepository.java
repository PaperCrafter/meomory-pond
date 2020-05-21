package com.nintynine.memorypond.Repository;

import com.nintynine.memorypond.Model.Comment;
import com.nintynine.memorypond.Model.Projection.CommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<CommentProjection> findAllByPostId(int postId);

    List<CommentProjection> findAllById(int commentId);

    Comment save(Comment comment);

    void deleteAllByPostId(int postId);
}
