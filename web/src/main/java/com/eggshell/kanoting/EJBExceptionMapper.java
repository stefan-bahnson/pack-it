package com.eggshell.kanoting;


import com.eggshell.kanoting.controller.objects.Error;
import com.eggshell.kanoting.exceptions.UnauthorizedException;

import javax.ejb.EJBException;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    @Override
    public Response toResponse(EJBException ex) {
        Throwable cause = ex.getCause();
        Response error = null;
        if(cause == null) {
            return error;
        } else if(cause instanceof EntityNotFoundException) {
            return Response.status(Response.Status.NO_CONTENT)
                    .header("cause", "No content: " + cause)
                    .build();
        } else if(cause instanceof UnauthorizedException) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(new Error(cause.getMessage()))
                    .build();
        } else {
            error = Response.serverError().header("cause", ex).build();
        }

        return error;
    }
}