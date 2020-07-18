package com.nintynine.memorypond.domain.entity;

import com.nintynine.memorypond.domain.value.Role;
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
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

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
