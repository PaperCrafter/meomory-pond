package com.nintynine.memorypond.Repository;

import com.nintynine.memorypond.Model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
