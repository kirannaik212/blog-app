package com.blogApp.exceptions;

import com.blogApp.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
        String exMessage = ex.getMessage();
        ApiResponse response=new ApiResponse(exMessage,false);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
}
