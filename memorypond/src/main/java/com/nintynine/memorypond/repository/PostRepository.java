package com.nintynine.memorypond.repository;

import com.nintynine.memorypond.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @EntityGraph(attributePaths = {"question", "question.category"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select post from Post post")
    Page<Post> findAll(Pageable pageable);

    Optional<Post> findById(Integer id);

    List<Post> findAllById(Integer id);

    int countPostsByMemberUsername(String username);
}


//    @EntityGraph(attributePaths = {"question", "question.category"})
//    @Query("select post from Post post")
//@Query(value = "select post from Post post join fetch post.question question join fetch question.category category",
//        countQuery = "select count(post) from Post post")