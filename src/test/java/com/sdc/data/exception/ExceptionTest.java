package com.sdc.data.exception;

import org.junit.jupiter.api.Test;
import javax.ws.rs.core.Response.Status;

import com.sdc.data.exception.type.BadRequestException;
import com.sdc.data.exception.type.ConstraintViolationException;
import com.sdc.data.exception.type.EntityAlreadyExistException;
import com.sdc.data.exception.type.EntityNotFoundException;
import com.sdc.data.model.SampleEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionTest {

    private static final String BAD_REQUEST = "Bad Request";
    private static final String NOT_FOUND = "Not Found";
    private static final String CONFLICT = "Entity already exists";
    private static final String INVALID_PARAMETER = "Invalid Parameter";
    private static final Class<?> ENTITY = SampleEntity.class;

    @Test
    public void shouldBuildBadRequestException() {
        BadRequestException badRequestException = new BadRequestException(BAD_REQUEST, ENTITY);

        assertEquals(badRequestException.getErrorCode(), String.valueOf(Status.BAD_REQUEST.getStatusCode()));
        assertEquals(badRequestException.getEntityClass(), ENTITY);
        assertEquals(badRequestException.getMessage(), BAD_REQUEST);
    }

    @Test
    public void shouldBuildNotFoundExceptionWithOnlyMessage() {
        EntityNotFoundException exception = new EntityNotFoundException(NOT_FOUND);

        assertEquals(exception.getMessage(), NOT_FOUND);
        assertEquals(exception.getErrorCode(), String.valueOf(Status.NOT_FOUND.getStatusCode()));
        assertEquals(exception.getEntityClass(), null);
        assertEquals(exception.getStatus(), Status.NOT_FOUND);
    }

    @Test
    public void shouldBuildNotFoundException() {
        EntityNotFoundException exception = new EntityNotFoundException(NOT_FOUND, ENTITY);

        assertEquals(exception.getMessage(), NOT_FOUND);
        assertEquals(exception.getErrorCode(), String.valueOf(Status.NOT_FOUND.getStatusCode()));
        assertEquals(exception.getEntityClass(), ENTITY);
    }

    @Test
    public void shouldBuildEntityAlreadyExistException() {
        EntityAlreadyExistException exception = new EntityAlreadyExistException(CONFLICT, ENTITY);

        assertEquals(exception.getMessage(), CONFLICT);
        assertEquals(exception.getErrorCode(), String.valueOf(Status.CONFLICT.getStatusCode()));
        assertEquals(exception.getEntityClass(), ENTITY);
        assertEquals(exception.getStatus(), Status.CONFLICT);
    }

    @Test
    public void shouldBuildConstraintViolationException() {
        ConstraintViolationException vioation = new ConstraintViolationException(INVALID_PARAMETER);

        assertEquals(vioation.getMessage(), INVALID_PARAMETER);
        assertEquals(vioation.getErrorCode(), "Validation error");
        assertEquals(vioation.getEntityClass(), null);
    }
}
