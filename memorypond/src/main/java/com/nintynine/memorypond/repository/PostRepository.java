package com.nintynine.memorypond.repository;

import com.nintynine.memorypond.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @EntityGraph(attributePaths = {"question", "question.category"})
    @Query("select post from Post post")
//    @Query("SELECT post FROM Post post JOIN FETCH post.question question JOIN FETCH question.category)
    Page<Post> findAllBy(Pageable pageable);

    Optional<Post> findById(Integer id);

    List<Post> findAllById(Integer id);

    int countPostsByMemberUsername(String username);
}
