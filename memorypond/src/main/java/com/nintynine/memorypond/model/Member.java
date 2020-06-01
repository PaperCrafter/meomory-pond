package com.nintynine.memorypond.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String createAt;

    private String updateAt;

    private Boolean hasVisited;

//    @OneToMany(mappedBy = "member")
//    private List<Post> posts = new ArrayList<Post>();
//
//    @OneToMany(mappedBy = "member")
//    private List<Comment> comments = new ArrayList<Comment>();
//
//    @OneToMany(mappedBy = "member")
//    private List<Like> likes = new ArrayList<Like>();

    public Member(String username, String password, String createAt, String updateAt, boolean hasVisited) {
        this.username = username;
        this.password = password;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.hasVisited = hasVisited;
    }
}
