package com.nintynine.memorypond.Repository;

import com.nintynine.memorypond.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findById(int id);

    List<Question> findAllByCategoryId(int categoryId);
}
