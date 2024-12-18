package com.sdc.data.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    private String errorCode;
    private HttpStatus status;
    private Class<?> entityClass;

    public BaseException() {

    }

    public BaseException(String message, String errorCode, HttpStatus status, Class<?> entityClass) {
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

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

}
