package com.sdc.data.exception.type;

import javax.ws.rs.core.Response.Status;

public class ConstraintViolationException extends BaseException {

    public ConstraintViolationException(String message) {
        super(message, "Validation error", Status.BAD_REQUEST, null);
    }
}
