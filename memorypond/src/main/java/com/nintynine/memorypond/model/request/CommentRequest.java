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

    @NotEmpty
    private int postId;

    @Setter
    private int memberId;

    public static Comment toComment(EntityManager em, CommentRequest commentRequest){
        LocalDateTime localDateTime = LocalDateTime.now();
        return Comment.builder()
                .member(em.getReference(Member.class, commentRequest.memberId))
                .post(em.getReference(Post.class, commentRequest.getPostId()))
                .content(commentRequest.content)
                .createAt(localDateTime.toString())
                .updateAt(localDateTime.toString())
                .build();
    }
}
