package com.nintynine.memorypond.model.request;

import com.nintynine.memorypond.model.Member;
import com.nintynine.memorypond.model.Post;
import com.nintynine.memorypond.model.Question;
import lombok.*;

import javax.persistence.EntityManager;
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

    public static Post toPost(EntityManager em, PostRequest postRequest){
        LocalDateTime requestedTime = LocalDateTime.now();
        return Post.builder()
                .content(postRequest.getComment())
                .weight(postRequest.getWeight())
                .member(em.getReference(Member.class, postRequest.getMemberId()))
                .question(em.getReference(Question.class, postRequest.getQuestionId()))
                .createAt(requestedTime.toString())
                .updateAt(requestedTime.toString())
                .build();
    }
}
