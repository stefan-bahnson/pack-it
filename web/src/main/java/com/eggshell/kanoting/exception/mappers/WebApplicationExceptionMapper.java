package com.eggshell.kanoting.exception.mappers;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Write Javadoc...
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException ex) {
        String message = ex.getMessage();
        ResponseBuilder builder;
        ErrorMsg msg = new ErrorMsg(message);

        if (ex instanceof NotFoundException) {
            message = ex.getMessage();
            System.out.println("NotFoundException :" + message);
            msg.setMsg(message);
            builder = Response.status(Status.NOT_FOUND)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(msg);
        }
        else {
            System.out.println("WebApplicationException: " + message);
            builder = Response.status(Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(msg);
        }

        return builder.build();
    }
}
