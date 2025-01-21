package com.sdc.data.exception.type;

import javax.ws.rs.core.Response.Status;

public class BadRequestException extends BaseException {

    public BadRequestException(String message, Class<?> clazz) {
        super(message, String.valueOf(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST, clazz);
    }

    public BadRequestException(String message) {
        super(message, String.valueOf(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST, null);
    }
}
