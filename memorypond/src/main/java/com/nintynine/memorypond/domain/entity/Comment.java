package com.nintynine.memorypond.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String content;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "POST_ID")
    private Post post;
}
