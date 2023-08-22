package com.pblgllgs.securitysb3ss6unkown.exception;

public class SQLIntegrityConstraintViolationException extends RuntimeException{
    private String msg;
    public SQLIntegrityConstraintViolationException(String message) {
        super(message);
        this.msg = message;
    }
}
