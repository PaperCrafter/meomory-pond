package com.nintynine.memorypond.Model.Projection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface PostBoardProjection {
    Integer getId();

    String getContent();

    int getWeight();

    String getCreateAt();

    String getUpdateAt();

    default String getUsername() {
        return getMemberUsername();
    }

    @JsonIgnore
    String getMemberUsername();

    String getQuestionName();
}
