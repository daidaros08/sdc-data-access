package com.sdc.data.exception.mapper;

import java.util.Arrays;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sdc.data.exception.ErrorResponse;
import com.sdc.data.exception.type.BaseException;

@Provider
public class BaseExceptionMapper implements ExceptionMapper<BaseException> {

    @Override
    public Response toResponse(BaseException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatus(e.getStatus());
        response.setStatusCode(e.getErrorCode());
        response.setEntityClass(e.getEntityClass());

        return Response.status(e.getStatus())
            .entity(response)
            .build();
    }
}
