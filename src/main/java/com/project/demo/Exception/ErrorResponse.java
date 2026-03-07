package com.project.demo.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus statusCode;

    ErrorResponse(){
        this.timeStamp=LocalDateTime.now();
    }
    ErrorResponse(String error , HttpStatus statusCode){
        this.error=error;
        this.statusCode=statusCode;
    }
}
