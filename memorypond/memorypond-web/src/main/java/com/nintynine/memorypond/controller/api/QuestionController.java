package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.domain.response.QuestionResponse;
import com.nintynine.memorypond.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/api/categories/{categoryId}/questions")
    public List<QuestionResponse> getQuestions(@PathVariable int categoryId){
        return questionService.getQuestionsByCategoryId(categoryId);
    }
}
