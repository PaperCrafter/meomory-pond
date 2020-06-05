package com.nintynine.memorypond.model.response;

import com.nintynine.memorypond.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private int id;

    private String name;

    private int weight;

    private String categoryName;

    public static QuestionResponse of(Question question){
        return QuestionResponse.builder()
                .id(question.getId())
                .name(question.getName())
                .weight(question.getWeight())
                .categoryName(question.getCategory().getCategoryName())
                .build();
    }
}
