package com.nintynine.memorypond.domain.response;

import com.nintynine.memorypond.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Integer id;
    private String categoryName;
    public static CategoryResponse of(Category category){
        return new CategoryResponse(category.getId(), category.getCategoryName());
    }
}
