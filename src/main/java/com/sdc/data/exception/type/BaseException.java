package com.sdc.data.exception.type;

import javax.ws.rs.core.Response.Status;

public abstract class BaseException extends RuntimeException {

    private String errorCode;
    private Status status;
    private Class<?> entityClass;

    public BaseException() {
    }

    public BaseException(String message, String errorCode, Status status, Class<?> entityClass) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.entityClass = entityClass;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }
}
