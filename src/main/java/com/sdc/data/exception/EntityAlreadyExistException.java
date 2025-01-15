package com.sdc.data.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistException extends BaseException {

    public EntityAlreadyExistException(String message, Class<?> clazz) {
        super(message, HttpStatus.CONFLICT.getReasonPhrase(), HttpStatus.CONFLICT, clazz);
    }
}
