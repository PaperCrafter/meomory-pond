package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.model.Question;
import com.nintynine.memorypond.model.response.QuestionResponse;
import com.nintynine.memorypond.model.user.CustomUser;
import com.nintynine.memorypond.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
