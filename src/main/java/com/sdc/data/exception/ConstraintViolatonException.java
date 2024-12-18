package com.sdc.data.exception;

import org.springframework.http.HttpStatus;

public class ConstraintViolatonException extends BaseException {

    public ConstraintViolatonException(String message) {
        super(message, "Validation error", HttpStatus.BAD_REQUEST, null);
    }
}
