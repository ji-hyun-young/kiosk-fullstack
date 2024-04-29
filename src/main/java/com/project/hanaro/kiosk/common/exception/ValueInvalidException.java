package com.project.hanaro.kiosk.common.exception;

public class ValueInvalidException extends RuntimeException{

    public ValueInvalidException() {
        super("Invalid value");
    }

    public ValueInvalidException(String message) {
        super(message);
    }
}
