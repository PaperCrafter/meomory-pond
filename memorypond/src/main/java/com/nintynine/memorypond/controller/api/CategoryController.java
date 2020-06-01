package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.model.Category;
import com.nintynine.memorypond.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/api/categories")
    public List<Category> getCategories(){
        List<Category> categories = categoryService.getCategories();
        return categories;
    }
}
