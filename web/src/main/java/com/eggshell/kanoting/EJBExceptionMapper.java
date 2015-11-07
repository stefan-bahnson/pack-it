package com.eggshell.kanoting;


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
        Response unkownError = Response.serverError().header("cause", ex).build();


        if(cause == null) {
            return unkownError;
        }

        if(cause instanceof EntityNotFoundException) {
            return Response.status(Response.Status.NO_CONTENT)
                    .header("cause", "No content: " + cause)
                    .build();
        }

        return unkownError;
    }
}