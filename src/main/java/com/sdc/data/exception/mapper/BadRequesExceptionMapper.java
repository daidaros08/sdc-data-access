package com.sdc.data.exception.mapper;

import java.util.Arrays;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sdc.data.exception.ErrorResponse;
import com.sdc.data.exception.type.BadRequestException;

@Provider
public class BadRequesExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException ex) {

        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setStatusCode(ex.getErrorCode());
        response.setStatus(ex.getStatus());
        response.setEntityClass(ex.getEntityClass());

        return Response.status(Response.Status.BAD_REQUEST)
            .entity(response)
            .build();
    }
}
