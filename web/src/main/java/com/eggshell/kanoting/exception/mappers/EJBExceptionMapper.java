package com.eggshell.kanoting.exception.mappers;


import com.eggshell.kanoting.exception.mappers.objects.Error;
import com.eggshell.kanoting.exceptions.UnauthorizedException;

import javax.ejb.EJBException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
// Daniel Laine was here
@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    @Override
    public Response toResponse(EJBException ex) {
        Throwable cause = ex.getCause();
        Response error = null;

        if (cause instanceof EntityNotFoundException) {
            error = Response.status(Response.Status.NO_CONTENT)
                    .header("cause", "No content: " + cause)
                    .build();
        }
        else if (cause instanceof UnauthorizedException) {
            error = Response.status(Response.Status.FORBIDDEN)
                    .entity(new Error(cause.getMessage()))
                    .build();
        }
        else if (cause instanceof NoResultException) {
            error = Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error(cause.getMessage()))
                    .build();
        }
        else {
            error = Response.serverError().header("cause", ex).build();
        }

        return error;
    }
}