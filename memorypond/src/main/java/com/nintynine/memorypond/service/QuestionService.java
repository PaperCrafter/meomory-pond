package com.nintynine.memorypond.service;

import com.nintynine.memorypond.model.Question;
import com.nintynine.memorypond.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public List<Question> getQuestionsByCategoryId(int categoryId){
        return questionRepository.findAllByCategoryId(categoryId);
    }
}
