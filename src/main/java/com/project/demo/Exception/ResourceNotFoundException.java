package com.project.demo.Exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException{
    private HttpStatus status;

    public ResourceNotFoundException(String message){
        super(message);
        this.status=HttpStatus.NOT_FOUND;
    }
    ResourceNotFoundException(String message, HttpStatus status){
        super(message);
        this.status=status;
    }
    public HttpStatus getStatus(){
       return status;
    }

}
