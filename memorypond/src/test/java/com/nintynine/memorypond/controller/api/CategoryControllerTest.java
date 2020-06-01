package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.model.Category;
import com.nintynine.memorypond.service.CategoryService;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class CategoryControllerTest {
    @InjectMocks
    private CategoryController categoryContoller;
    
    @Mock
    private CategoryService categoryService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        categoryContoller = new CategoryController(categoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryContoller).build();
    }

    @Test
    public void getCategoriesTest() throws Exception {
        List<Category> givenCategories = EnhancedRandom.randomListOf(4, Category.class);
        given(categoryService.getCategories()).willReturn(givenCategories);

        mockMvc.perform(get("/api/categories").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isArray());
    }
}