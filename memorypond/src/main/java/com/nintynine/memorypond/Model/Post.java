package com.nintynine.memorypond.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nintynine.memorypond.Model.Request.PostRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    private String createAt;

    private String updateAt;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn
    private Member member;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn
    private Question question;

    public Post(String content, String createAt, String updateAt, Member member, Question question) {
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.member = member;
        this.question = question;
    }

    public Post(int id, String content, String createAt, String updateAt, Member member, Question question) {
        this.id = id;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.member = member;
        this.question = question;
    }
}
