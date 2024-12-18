package com.sdc.data.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {

    public BadRequestException(String message, String errorCode, Class<?> clazz) {
        super(message, errorCode, HttpStatus.BAD_REQUEST, clazz);
    }


    public BadRequestException(String message, String errorCode) {
        super(message, errorCode, HttpStatus.BAD_REQUEST, null);
    }
}
