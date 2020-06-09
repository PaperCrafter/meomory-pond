package com.nintynine.memorypond.model.request;

import com.nintynine.memorypond.model.Comment;
import com.nintynine.memorypond.model.Member;
import com.nintynine.memorypond.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentRequest {
    @NotNull
    private String content;

    @NotNull
    private Integer postId;

    @Setter
    private Integer memberId;

    public static Comment toComment(EntityManager em, CommentRequest commentRequest){
        LocalDateTime requestedTime = LocalDateTime.now();
        return Comment.builder()
                .member(em.getReference(Member.class, commentRequest.getMemberId()))
                .post(em.getReference(Post.class, commentRequest.getPostId()))
                .content(commentRequest.getContent())
                .createAt(requestedTime.toString())
                .updateAt(requestedTime.toString())
                .build();
    }
}
