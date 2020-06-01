package com.nintynine.memorypond.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    MEMBER("member"),
    ADMIN("admin");
    private String value;
}
