package com.sdc.data.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import com.sdc.data.model.SampleEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionTest {

    private static final String BAD_REQUEST = "Bad Request";
    private static final String NOT_FOUND = "Not Found";
    private static final String CONFLICT = "Entity already exists";
    private static final String INVALID_PARAMETER = "Invalid Parameter";
    private static String ERROR_CODE = "400_BAD_REQUEST";
    private static final Class<?> ENTITY = SampleEntity.class;

    @Test
    public void shouldBuildBadRequestException() {
        BadRequestException badRequestException = new BadRequestException(BAD_REQUEST, ERROR_CODE, ENTITY);

        assertEquals(badRequestException.getErrorCode(), ERROR_CODE);
        assertEquals(badRequestException.getEntityClass(), ENTITY);
        assertEquals(badRequestException.getMessage(), BAD_REQUEST);
    }

    @Test
    public void shouldBuildNotFoundExceptionWithOnlyMessage() {
        EntityNotFoundException exception = new EntityNotFoundException(NOT_FOUND);

        assertEquals(exception.getMessage(), NOT_FOUND);
        assertEquals(exception.getErrorCode(), HttpStatus.NOT_FOUND.getReasonPhrase());
        assertEquals(exception.getEntityClass(), null);
        assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldBuildNotFoundException() {
        EntityNotFoundException exception = new EntityNotFoundException(NOT_FOUND, ERROR_CODE, ENTITY);

        assertEquals(exception.getMessage(), NOT_FOUND);
        assertEquals(exception.getErrorCode(), ERROR_CODE);
        assertEquals(exception.getEntityClass(), ENTITY);
    }

    @Test
    public void shouldBuildEntityAlreadyExistException() {
        EntityAlreadyExistException exception = new EntityAlreadyExistException(CONFLICT, ENTITY);

        assertEquals(exception.getMessage(), CONFLICT);
        assertEquals(exception.getErrorCode(), HttpStatus.CONFLICT.getReasonPhrase());
        assertEquals(exception.getEntityClass(), ENTITY);
        assertEquals(exception.getStatus(), HttpStatus.CONFLICT);
    }

    @Test
    public void shouldBuildConstraintViolationException() {
        ConstraintViolatonException vioation = new ConstraintViolatonException(INVALID_PARAMETER);

        assertEquals(vioation.getMessage(), INVALID_PARAMETER);
        assertEquals(vioation.getErrorCode(), "Validation error");
        assertEquals(vioation.getEntityClass(), null);
    }
}
