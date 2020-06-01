package com.nintynine.memorypond.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn
    private Member member;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn
    private Post post;
}
