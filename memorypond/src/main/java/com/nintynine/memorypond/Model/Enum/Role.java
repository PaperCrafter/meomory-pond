package com.nintynine.memorypond.Model.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Getter
public enum Role {
    MEMBER("member"),
    ADMIN("admin");
    private String value;
}
