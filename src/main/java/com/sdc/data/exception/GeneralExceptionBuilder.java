package com.sdc.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionBuilder {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> buildEntityNotFoundException(String message, EntityNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
            message,
            ex.getErrorCode(),
            ex.getStatus(),
            ex.getEntityClass()
        );
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> buildEntityAlreadyExistException(String message, EntityAlreadyExistException ex) {
        ErrorResponse error = new ErrorResponse(
            message,
            ex.getErrorCode(),
            ex.getStatus(),
            ex.getEntityClass()
        );
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(ConstraintViolatonException.class)
    public ResponseEntity<ErrorResponse> buildConstraintViolationException(ConstraintViolatonException ex) {
        ErrorResponse error = new ErrorResponse(
            ex.getMessage(),
            ex.getErrorCode(),
            ex.getStatus(),
            ex.getEntityClass()
        );
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> buildBadRequestException(BadRequestException ex) {
        ErrorResponse error = new ErrorResponse(
            ex.getMessage(),
            ex.getErrorCode(),
            ex.getStatus(),
            ex.getEntityClass()
        );
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> buildGeneralException(BaseException ex) {
        ErrorResponse error = new ErrorResponse(
            ex.getMessage(),
            ex.getErrorCode(),
            ex.getStatus(),
            ex.getEntityClass()
        );
        return new ResponseEntity<>(error, ex.getStatus());
    }

    public static class ErrorResponse {
        private String message;
        private String code;
        private HttpStatus httpStatus;
        private long timestamp;
        private Class<?> entityClass;

        public ErrorResponse(String message, String code, HttpStatus httpStatus, Class<?> entityClass) {
            this.message = message;
            this.code = code;
            this.httpStatus = httpStatus;
            this.timestamp = System.currentTimeMillis();
            this.entityClass = entityClass;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public void setHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public Class<?> getEntityClass() {
            return entityClass;
        }

        public void setEntityClass(Class<?> entityClass) {
            this.entityClass = entityClass;
        }
    }
}
