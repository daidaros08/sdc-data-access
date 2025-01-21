package com.sdc.data.exception.mapper;

import java.util.Arrays;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sdc.data.exception.ErrorResponse;
import com.sdc.data.exception.type.EntityAlreadyExistException;

@Provider
public class EntityAlreadyExistExceptionMapper implements ExceptionMapper<EntityAlreadyExistException> {

    @Override
    public Response toResponse(EntityAlreadyExistException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setStatusCode(ex.getErrorCode());
        response.setStatus(ex.getStatus());
        response.setEntityClass(ex.getEntityClass());

        return Response.status(Response.Status.CONFLICT)
            .entity(response)
            .build();
    }
}
