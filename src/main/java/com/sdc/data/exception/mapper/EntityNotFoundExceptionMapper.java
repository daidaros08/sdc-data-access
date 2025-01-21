package com.sdc.data.exception.mapper;

import java.util.Arrays;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sdc.data.exception.ErrorResponse;
import com.sdc.data.exception.type.EntityNotFoundException;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {
    @Override
    public Response toResponse(EntityNotFoundException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatusCode(e.getErrorCode());
        response.setStatus(e.getStatus());
        response.setEntityClass(e.getEntityClass());

        return Response.status(Response.Status.NOT_FOUND)
            .entity(response)
            .build();
    }
}
