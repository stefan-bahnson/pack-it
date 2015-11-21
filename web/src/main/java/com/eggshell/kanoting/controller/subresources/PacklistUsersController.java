package com.eggshell.kanoting.controller.subresources;

import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.PackListRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Write Javadoc...
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacklistUsersController {

    @Inject
    PackListRepository packListRepo;

    /*
        add one user to packlist with path params
        add one user to packlist with form params
        get all packlists where user is authorized
        todo: get all authorized users for one packlist
        todo: remove one authorized user from packlist
        todo: remove all authorized users from packlist

    */

    @PUT
    public Response addUsersToPacklist(@PathParam("packlistId") long packlistId, List<User> users) {
        packListRepo.addUsersToPacklist(users, packlistId);

        return Response.ok().build();
    }

    @PUT
    @Path("{userId}")
    public Response addUserToPacklist(@PathParam("packlistId") long packlistId, @PathParam("userId") long userId) {
        packListRepo.addUserToPacklist(userId, packlistId);

        return Response.ok().build();
    }

    @GET
    @Path("{authUserId}")
    public List<PackList> getPacklistsByAuthUserId(@PathParam("authUserId") long authUserId) {
        return packListRepo.findPacklistsByAuthUserId(authUserId);
    }

}
