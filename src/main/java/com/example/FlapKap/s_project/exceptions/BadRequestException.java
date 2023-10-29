package com.example.FlapKap.s_project.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String messages) {
        super(messages);
    }
}