package com.example.mini_project.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidPasswordException  extends RuntimeException {
    public InvalidPasswordException(String passwordIsIncorrect) {
    }
}
