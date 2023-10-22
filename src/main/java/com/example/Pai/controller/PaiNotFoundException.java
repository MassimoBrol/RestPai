package com.example.Pai.controller;

public class PaiNotFoundException extends RuntimeException {

    public PaiNotFoundException(String message, Throwable cause) {
        super(message, cause);

    }

    public PaiNotFoundException(String message) {
        super(message);

    }

    public PaiNotFoundException(Throwable cause) {
        super(cause);

    }

    public PaiNotFoundException() {
        super();

    }

    public PaiNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

}
