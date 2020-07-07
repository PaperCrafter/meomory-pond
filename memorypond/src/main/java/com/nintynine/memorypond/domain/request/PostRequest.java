package com.nintynine.memorypond.domain.request;

import com.nintynine.memorypond.domain.entity.Member;
import com.nintynine.memorypond.domain.entity.Post;
import com.nintynine.memorypond.domain.entity.Question;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    @NotNull
    private String comment;

    @Setter
    private Integer memberId;

    @NotNull
    private Integer questionId;

    @Setter
    private int weight;

    public static Post toPost(PostRequest postRequest){
        return Post.builder()
                .content(postRequest.getComment())
                .weight(postRequest.getWeight())
                .member(new Member(postRequest.getMemberId()))
                .question(new Question(postRequest.getQuestionId()))
                .build();
    }
}
