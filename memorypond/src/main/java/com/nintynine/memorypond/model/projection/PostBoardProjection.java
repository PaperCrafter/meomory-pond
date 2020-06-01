package com.nintynine.memorypond.model.projection;

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
