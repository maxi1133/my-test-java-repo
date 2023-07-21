package com.zaka.exception;

public class BaseException extends Exception {
    private String exception;

    public BaseException(String message) {
        this.exception = message;
    }
}
