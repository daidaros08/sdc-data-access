package com.sdc.data.exception.mapper;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sdc.data.exception.ErrorResponse;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final String VALIDATION_ERROR = "VALIDATION_ERROR";

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toList());


        ErrorResponse response = new ErrorResponse();
        response.setMessages(errors);
        response.setStatusCode(VALIDATION_ERROR);
        response.setStatus(Response.Status.BAD_REQUEST);

        return Response.status(response.getStatus())
            .entity(response)
            .build();
    }
}
