package com.eggshell.kanoting.controller.mappers;


import com.eggshell.kanoting.model.exceptions.UnauthorizedException;

import javax.ejb.EJBException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    @Override
    public Response toResponse(EJBException ex) {
        Throwable cause = ex.getCause();
        Response response = Response.serverError().header("cause", ex).build();

        if (cause instanceof EntityNotFoundException) {
            response = Response.status(Response.Status.NO_CONTENT)
                    .header("cause", "No content: " + cause)
                    .build();
        }
        else if (cause instanceof UnauthorizedException) {
            response = Response.status(Response.Status.FORBIDDEN)
                    .entity(new Error(cause.getMessage()))
                    .build();
        }
        else if (cause instanceof NoResultException) {
            response = Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error(cause.getMessage()))
                    .build();
        }

        return response;
    }
}