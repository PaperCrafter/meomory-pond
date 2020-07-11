package com.nintynine.memorypond.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
<<<<<<< HEAD:memorypond/src/main/java/com/nintynine/memorypond/domain/entity/Question.java
import java.util.ArrayList;
=======
>>>>>>> 8dbe5ea692db25efb21a0eb2658d196666f53a63:memorypond/src/main/java/com/nintynine/memorypond/model/Question.java
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int weight;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "question")
    private List<Post> postList = new ArrayList<>();

    public Question(int id){
        this.id = id;
    }
}
