package com.sdc.data.exception.type;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(String message, Class<?> entityClass) {
        super(message, String.valueOf(NOT_FOUND.getStatusCode()), NOT_FOUND, entityClass);
    }

    public EntityNotFoundException(String message) {
        super(message, String.valueOf(NOT_FOUND.getStatusCode()), NOT_FOUND, null);
    }
}
