package com.nintynine.memorypond.model.response;

import com.nintynine.memorypond.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private int id;

    private String content;

    private int weight;

    private String createAt;

    private String UpdateAt;

    private String username;

    private String questionName;

    public static PostResponse of(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .weight(post.getWeight())
                .createAt(post.getCreateAt())
                .UpdateAt(post.getUpdateAt())
                .username(post.getMember().getUsername())
                .questionName(post.getQuestion().getName())
                .build();
    }
}
