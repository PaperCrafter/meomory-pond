package com.nintynine.memorypond.domain.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username){
        super("No username " + username + "found!");
    }
}
