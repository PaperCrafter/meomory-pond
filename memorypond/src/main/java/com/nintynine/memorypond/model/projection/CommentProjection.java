package com.nintynine.memorypond.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CommentProjection {
    int getId();

    String getContent();

    default String getUsername(){
        return getMemberUsername();
    };

    @JsonIgnore
    String getMemberUsername();

    int getPostId();

    String getCreateAt();

    String getUpdateAt();

    //method used for test
    void setId(int id);

    void setPostId(int postId);

    void setContent(String content);
}
