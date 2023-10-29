package com.example.FlapKap.s_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorBody> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request ){
        ErrorBody errorObject = new ErrorBody();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimeStamp(new Date());
        return new ResponseEntity<>(errorObject,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SellerNotFoundException.class)
    public ResponseEntity<ErrorBody> handleSellerNotFoundException(ProductNotFoundException ex, WebRequest request ){
        ErrorBody errorObject = new ErrorBody();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimeStamp(new Date());
        return new ResponseEntity<>(errorObject,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SellerNotFoundException.class)
    public ResponseEntity<ErrorBody> handleBadRequestException(BadRequestException ex, WebRequest request ){
        ErrorBody errorObject = new ErrorBody();
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimeStamp(new Date());
        return new ResponseEntity<>(errorObject,HttpStatus.BAD_REQUEST);
    }
}
