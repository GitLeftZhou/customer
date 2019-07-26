package com.zhou.customer.exception;

public class BussException extends RuntimeException {

    public BussException() {
        super("业务操作异常了");
    }

    public BussException(String message) {
        super(message);
    }
}
