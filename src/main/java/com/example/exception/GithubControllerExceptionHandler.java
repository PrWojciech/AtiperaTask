package com.example.exception;


import com.example.model.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GithubControllerExceptionHandler {

    @ExceptionHandler(GithubUserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(GithubUserNotFoundException ex) {
        ApiError apiError = new ApiError(ex.getStatus().value(), ex.getMessage());
        return new ResponseEntity<>(apiError, ex.getStatus());
    }
}