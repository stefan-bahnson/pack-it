package com.eggshell.kanoting.controller.subresources;

import com.eggshell.kanoting.repository.PackListRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Write Javadoc...
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacklistUsersController {

    @Inject
    PackListRepository packListRepo;

    @PUT
    @Path("{userId}")
    public Response addUserToPacklist(@PathParam("packlistId") long packlistId, @PathParam("userId") long userId) {
        packListRepo.addUserToPacklist(userId, packlistId);

        return Response.ok().build();
    }
}
