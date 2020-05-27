package com.nintynine.memorypond.Service;

import com.nintynine.memorypond.Model.Question;
import com.nintynine.memorypond.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

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
