package com.nintynine.memorypond.service;

import com.nintynine.memorypond.domain.response.QuestionResponse;
import com.nintynine.memorypond.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public List<QuestionResponse> getQuestionsByCategoryId(int categoryId){
        return questionRepository.findAllByCategoryId(categoryId).stream()
                .map((category)-> QuestionResponse.of(category))
                .collect(Collectors.toList());
    }
}
