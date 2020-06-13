package com.nintynine.memorypond.model;

import com.nintynine.memorypond.model.request.CommentRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String content;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "postId")
    private Post post;

    @Setter
    private String createAt;

    @Setter
    private String updateAt;
}
