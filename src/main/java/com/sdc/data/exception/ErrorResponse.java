package com.sdc.data.exception;

import java.util.List;
import java.util.Objects;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class ErrorResponse {

    private List<String> messages;
    private String message;
    private String statusCode;
    private Status status;
    private Class<?> entityClass;

    public ErrorResponse() {
    }

    public ErrorResponse(List<String> messages, String statusCode, Status status, Class<?> entityClass) {
        this.messages = messages;
        this.statusCode = statusCode;
        this.status = status;
        this.entityClass = entityClass;
    }

    public ErrorResponse(String message, String statusCode, Status status, Class<?> entityClass) {
        this.message = message;
        this.statusCode = statusCode;
        this.status = status;
        this.entityClass = entityClass;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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

    @JsonGetter("entityClass")
    public String getEntityClassName() {
        return Objects.nonNull(entityClass) ? entityClass.getSimpleName() : null;
    }
}
