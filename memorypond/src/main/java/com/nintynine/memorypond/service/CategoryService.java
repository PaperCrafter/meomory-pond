package com.nintynine.memorypond.service;

import com.nintynine.memorypond.model.Category;
import com.nintynine.memorypond.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService  {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
}
