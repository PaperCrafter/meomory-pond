package com.nintynine.memorypond.domain.request;

import com.nintynine.memorypond.domain.entity.Comment;
import com.nintynine.memorypond.domain.entity.Member;
import com.nintynine.memorypond.domain.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public static Comment toComment(CommentRequest commentRequest){
        return Comment.builder()
                .member(new Member(commentRequest.getMemberId()))
                .post(new Post(commentRequest.getPostId()))
                .content(commentRequest.getContent())
                .build();
    }
}
