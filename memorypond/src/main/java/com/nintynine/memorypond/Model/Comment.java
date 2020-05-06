package com.nintynine.memorypond.Model;

import com.nintynine.memorypond.Model.Projection.CommentProjection;
import com.nintynine.memorypond.Model.Request.CommentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String content;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn
    private Member member;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn
    private Post post;

    private String createAt;

    private String updateAt;

    public Comment(CommentRequest commentRequest){
        this.member = new Member();
        this.member.setId(commentRequest.getMemberId());
        this.post = new Post();
        this.post.setId(commentRequest.getPostId());
        this.content = commentRequest.getContent();
        LocalDateTime localDateTime = LocalDateTime.now();
        this.createAt = localDateTime.toString();
        this.updateAt = localDateTime.toString();
    }
}
