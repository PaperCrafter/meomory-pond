package com.nintynine.memorypond.controller.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionAdvice extends ResponseEntityExceptionHandler {
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.OK;



}
