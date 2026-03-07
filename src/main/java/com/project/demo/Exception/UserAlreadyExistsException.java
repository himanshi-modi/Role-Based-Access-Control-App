package com.project.demo.Exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends RuntimeException{
    private HttpStatus status;
    public UserAlreadyExistsException(String message){
        super(message);
        this.status=HttpStatus.NOT_FOUND;
    }

}
