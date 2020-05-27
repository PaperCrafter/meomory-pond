package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.Model.Question;
import com.nintynine.memorypond.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/api/categories/{categoryId}/questions")
    public List<Question> getQuestions(@PathVariable int categoryId){
        return questionService.getQuestionsByCategoryId(categoryId);
    }
}
