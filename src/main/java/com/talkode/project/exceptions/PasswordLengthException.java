package com.talkode.project.exceptions;

public class PasswordLengthException extends RuntimeException {
    public PasswordLengthException() {
        super("Your password must contain at least 8 characters");
    }
}
