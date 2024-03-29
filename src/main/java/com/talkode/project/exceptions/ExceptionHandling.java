package com.talkode.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.talkode.project.exceptions.NameLengthException;


@ControllerAdvice
public class ExceptionHandling {
    @ResponseBody
    @ExceptionHandler(NameLengthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String nameLengthHandler(NameLengthException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}
