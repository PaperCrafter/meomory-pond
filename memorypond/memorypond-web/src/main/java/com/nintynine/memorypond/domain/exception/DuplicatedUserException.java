package com.nintynine.memorypond.domain.exception;

public class DuplicatedUserException extends RuntimeException {
    public DuplicatedUserException(){
        super("duplicated username");
    }
}
