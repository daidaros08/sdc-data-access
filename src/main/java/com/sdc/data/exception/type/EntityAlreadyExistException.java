package com.sdc.data.exception.type;

import static javax.ws.rs.core.Response.Status.CONFLICT;

public class EntityAlreadyExistException extends BaseException {

    public EntityAlreadyExistException(String message, Class<?> clazz) {
        super(message, String.valueOf(CONFLICT.getStatusCode()), CONFLICT, clazz);
    }
}
