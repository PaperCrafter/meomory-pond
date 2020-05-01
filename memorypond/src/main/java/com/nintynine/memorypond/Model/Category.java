package com.nintynine.memorypond.Model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String categoryName;

    @OneToMany(mappedBy="category")
    private List<Question> questions = new ArrayList<Question>();
}
