package com.itx.pricetest.infrastructure.in.controller.config;

public class UnAuthorizedException extends RuntimeException {

    private String message;

    public UnAuthorizedException(String message) {
        super(message);
        this.message = message;
    }

    public UnAuthorizedException() {
    }

}