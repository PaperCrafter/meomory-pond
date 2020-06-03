package com.nintynine.memorypond.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface PostPageProjection {
    Integer getId();

    String getContent();

    String getCreateAt();

    String getUpdateAt();

    default String getUsername() {
        return getMemberUsername();
    }

    @JsonIgnore
    String getMemberUsername();

    String getQuestionName();
}