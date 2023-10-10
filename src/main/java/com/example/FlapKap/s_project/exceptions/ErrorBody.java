package com.example.FlapKap.s_project.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorBody {
    private Integer statusCode;
    private String message;
    private Date timeStamp;
}
