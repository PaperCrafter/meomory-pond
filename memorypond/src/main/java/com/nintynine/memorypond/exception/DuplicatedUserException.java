package com.nintynine.memorypond.exception;

public class DuplicatedUserException extends RuntimeException{
    public DuplicatedUserException(){
        super("username duplicated");
    }
}
