package com.nintynine.memorypond.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    private String comment;

    private String username;

    private int questionId;
}
