package com.talkode.project.exceptions;

public class TokenValidationException extends RuntimeException {
    public TokenValidationException(String msg) {
        super(msg);
    }
}
