package com.nintynine.memorypond.Repository;

import com.nintynine.memorypond.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
