package com.example.FlapKap.s_project.exceptions;

public class SellerNotFoundException extends RuntimeException{
    private static final long serialVersionUTD=1;

    public SellerNotFoundException (String message){
        super(message);
    }
}
