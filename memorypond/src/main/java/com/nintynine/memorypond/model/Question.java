package com.nintynine.memorypond.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int weight;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn
    private Category category;
}