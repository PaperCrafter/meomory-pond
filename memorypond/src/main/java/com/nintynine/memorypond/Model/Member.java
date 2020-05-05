package com.nintynine.memorypond.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String createAt;

    private String updateAt;

//    @OneToMany(mappedBy = "member")
//    private List<Post> posts = new ArrayList<Post>();
//
//    @OneToMany(mappedBy = "member")
//    private List<Comment> comments = new ArrayList<Comment>();
//
//    @OneToMany(mappedBy = "member")
//    private List<Like> likes = new ArrayList<Like>();

    public Member(String username, String password, String createAt, String updateAt) {
        this.username = username;
        this.password = password;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreateAt(String createAd){
        this.createAt = createAd;
    }

    public void setUpdateAt(String updateAt){
        this.updateAt = updateAt;
    }
}
