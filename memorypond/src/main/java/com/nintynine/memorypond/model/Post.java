package com.nintynine.memorypond.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    private int weight;

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

    public Post(int id, String content, int weight, String createAt, String updateAt, Member member, Question question) {
        this.id = id;
        this.content = content;
        this.weight = weight;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.member = member;
        this.question = question;
    }
}
