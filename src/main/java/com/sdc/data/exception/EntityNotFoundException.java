package com.sdc.data.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(String message, String errorCode, Class<?> entityClass) {
        super(message, errorCode, HttpStatus.NOT_FOUND, entityClass);
    }

    public EntityNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND, null);
    }
}
