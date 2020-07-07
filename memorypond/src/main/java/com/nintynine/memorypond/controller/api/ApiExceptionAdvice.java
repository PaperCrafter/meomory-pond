package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.domain.exception.DuplicatedUserException;
import com.nintynine.memorypond.domain.exception.ResourceNotFoundException;
import com.nintynine.memorypond.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class,
            UserNotFoundException.class})
    public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatedUserException.class)
    public ResponseEntity handleDuplicatedUserException(ResourceNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
