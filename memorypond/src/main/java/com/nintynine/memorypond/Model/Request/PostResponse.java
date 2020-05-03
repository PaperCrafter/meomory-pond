package com.nintynine.memorypond.Model.Request;

import com.nintynine.memorypond.Model.Post;

public class PostResponse {
    private int postId;

    private String questionName;

    private String content;

    private String createAt;

    private String updateAt;

    private int commentNum;

    private int likeNum;

    public PostResponse(Post post){
        this.postId = post.getId();
        this.questionName = post.getQuestion().getName();
        this.content = post.getContent();
        this.createAt = post.getCreateAt();
        this.updateAt = post.getUpdateAt();
        this.commentNum = post.getComments().size();
        this.likeNum = post.getLikes().size();
    }
}
