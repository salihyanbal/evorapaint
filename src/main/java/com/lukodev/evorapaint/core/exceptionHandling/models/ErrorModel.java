package com.lukodev.evorapaint.core.exceptionHandling.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorModel {
    private HttpStatus httpStatus;

    private LocalDateTime timestamp;

    private String header;

    private String[] messages;

    public ErrorModel(){

    }

    public ErrorModel(HttpStatus httpStatus, String header, String[] messages) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.header = header;
        this.messages = messages;
    }
}
