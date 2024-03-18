package com.kkoksal.exception;

public class CustomTokenExpiredException extends RuntimeException {
    public CustomTokenExpiredException(String message) {
        super(message);
    }
}
