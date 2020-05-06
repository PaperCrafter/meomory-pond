package com.nintynine.memorypond.Model.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CommentRequest {
    private String content;

    private int postId;

    @Setter
    private int memberId;
}
