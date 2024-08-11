package com.example.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class GithubUserNotFoundException extends RuntimeException {
    private final HttpStatus status;

    public GithubUserNotFoundException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}