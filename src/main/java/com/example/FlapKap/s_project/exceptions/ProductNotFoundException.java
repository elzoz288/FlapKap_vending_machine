package com.example.FlapKap.s_project.exceptions;

public class ProductNotFoundException extends RuntimeException{
    private static final long serialVersionUTD=1;

    public ProductNotFoundException (String message){
        super(message);
    }

}
