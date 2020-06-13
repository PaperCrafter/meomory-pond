package com.nintynine.memorypond.model.request;

import com.nintynine.memorypond.model.Comment;
import com.nintynine.memorypond.model.Member;
import com.nintynine.memorypond.model.Post;
import com.nintynine.memorypond.util.ModelMapperUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

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

    public static Comment toComment(CommentRequest commentRequest){
        LocalDateTime requestedTime = LocalDateTime.now();
        return Comment.builder()
                .member(new Member(commentRequest.getMemberId()))
                .post(new Post(commentRequest.getPostId()))
                .content(commentRequest.getContent())
                .createAt(requestedTime.toString())
                .updateAt(requestedTime.toString())
                .build();
    }
}
