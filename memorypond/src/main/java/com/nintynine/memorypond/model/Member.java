package com.nintynine.memorypond.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(
        name="USERNAME_UNIQUE",
        columnNames = {"username"}
)})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String createAt;

    private String updateAt;

    private Boolean hasVisited;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<Post>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "member")
    private List<Like> likes = new ArrayList<Like>();

    public Member(int id){
        this.id = id;
    }
}
