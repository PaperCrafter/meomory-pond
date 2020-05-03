package com.nintynine.memorypond.Service;

import com.nintynine.memorypond.Model.Category;
import com.nintynine.memorypond.Repository.CategoryRepository;
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
