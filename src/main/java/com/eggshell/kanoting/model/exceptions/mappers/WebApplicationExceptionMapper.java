package com.eggshell.kanoting.model.exceptions.mappers;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Write Javadoc...
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException ex) {
        Throwable cause = ex.getCause();
        Response response = Response.serverError().header("cause", ex).build();

        if (ex instanceof NotFoundException) {
            response = Response.status(Response.Status.NO_CONTENT)
                    .header("cause", "could not find that resource: " + cause)
                    .build();
        }

        return response;
    }
}
