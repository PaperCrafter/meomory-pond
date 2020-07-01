package com.nintynine.memorypond.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    private int weight;

    private String createAt;

    private String updateAt;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Member member;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Question question;

    public Post(int id){
        this.id = id;
    }

    public Post(String content, String createAt, String updateAt, Member member, Question question) {
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.member = member;
        this.question = question;
    }
}
