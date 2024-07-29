package com.simplifiedx.springsecurity.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) { super(msg); }
}
