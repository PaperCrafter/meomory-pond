package com.nintynine.memorypond.Repository;

import com.nintynine.memorypond.Model.Projection.PostBoardProjection;
import com.nintynine.memorypond.Model.Projection.PostPageProjection;
import com.nintynine.memorypond.Model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<PostPageProjection> findAllBy(Pageable pageable);

    List<PostBoardProjection> findAllById(Integer id);

    int countPostsByMemberUsername(String username);
}
