package com.nintynine.memorypond.repository;

<<<<<<< HEAD
import com.nintynine.memorypond.domain.entity.Post;
=======
import com.nintynine.memorypond.model.Post;
>>>>>>> 8dbe5ea692db25efb21a0eb2658d196666f53a63
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
<<<<<<< HEAD
    @EntityGraph(attributePaths = {"question", "question.category"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select post from Post post")
    Page<Post> findAll(Pageable pageable);
=======
    @EntityGraph(attributePaths = {"question", "question.category"})
    @Query("select post from Post post")
//    @Query("SELECT post FROM Post post JOIN FETCH post.question question JOIN FETCH question.category)
    Page<Post> findAllBy(Pageable pageable);
>>>>>>> 8dbe5ea692db25efb21a0eb2658d196666f53a63

    Optional<Post> findById(Integer id);

    List<Post> findAllById(Integer id);

    int countPostsByMemberUsername(String username);
}


//    @EntityGraph(attributePaths = {"question", "question.category"})
//    @Query("select post from Post post")
//@Query(value = "select post from Post post join fetch post.question question join fetch question.category category",
//        countQuery = "select count(post) from Post post")