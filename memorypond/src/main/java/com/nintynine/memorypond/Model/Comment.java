package com.nintynine.memorypond.Model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String categoryName;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn
    private Member member;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn
    private Post post;
}
