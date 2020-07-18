package com.nintynine.memorypond.service;

import com.nintynine.memorypond.domain.entity.Category;
import com.nintynine.memorypond.domain.response.CategoryResponse;
import com.nintynine.memorypond.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService  {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryResponse> getCategories(){
        return categoryRepository.findAll().stream()
                .map((entity)->CategoryResponse.of(entity)).collect(Collectors.toList());
    }
}
