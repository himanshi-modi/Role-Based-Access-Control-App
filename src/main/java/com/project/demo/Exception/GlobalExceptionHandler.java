package com.project.demo.Exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex){
        ErrorResponse errorResponse=new ErrorResponse("Authentication Failed: "+ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException ex){
        ErrorResponse errorResponse=new ErrorResponse("Invalid JWT Token: "+ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex){
        ErrorResponse errorResponse=new ErrorResponse("Access Denied: "+ex.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(errorResponse,HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        ErrorResponse errorResponse=new ErrorResponse("Resource Not Found: "+ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception ex){
        ErrorResponse errorResponse=new ErrorResponse("Unexpected Error : "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        ErrorResponse errorResponse=new ErrorResponse("User Already exists : "+ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);

    }

}
