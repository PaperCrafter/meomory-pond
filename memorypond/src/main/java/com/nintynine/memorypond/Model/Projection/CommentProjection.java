package com.nintynine.memorypond.Model.Projection;

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
}
