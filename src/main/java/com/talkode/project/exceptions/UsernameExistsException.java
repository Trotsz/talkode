package com.talkode.project.exceptions;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException() {
        super("The username already exists.");
    }
}
