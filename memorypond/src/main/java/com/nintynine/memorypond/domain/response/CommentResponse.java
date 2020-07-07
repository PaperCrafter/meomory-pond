package com.nintynine.memorypond.domain.response;

import com.nintynine.memorypond.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private int id;

    private String content;

    private String username;

    private int postId;

    private String createAt;

    private String updateAt;

    public static CommentResponse of(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .username(comment.getMember().getUsername())
                .postId(comment.getPost().getId())
                .content(comment.getContent())
                .build();
    }
}