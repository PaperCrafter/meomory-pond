package com.nintynine.memorypond.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequest {
    private String comment;

    private String username;

    private int questionId;

    public PostRequest(String comment, String username, int questionId) {
        this.comment = comment;
        this.username = username;
        this.questionId = questionId;
    }
}
