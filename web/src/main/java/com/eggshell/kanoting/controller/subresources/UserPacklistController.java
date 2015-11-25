package com.eggshell.kanoting.controller.subresources;

import com.eggshell.kanoting.model.Packlist;
import com.eggshell.kanoting.repository.PackListRepository;
import com.eggshell.kanoting.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

/**
 * Write Javadoc...
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserPacklistController {

    @Inject
    UserRepository userRepository;

    @Inject
    PackListRepository packListRepository;

    @POST
    public Response create(@PathParam("userId") long userId, Packlist packlist) {
        packListRepository.addPacklistToUser(userId, packlist);

        return Response.status(Status.CREATED).build();
    }

    @GET
    public Response getAll(@PathParam("userId") long id) {
        List<Packlist> userPacklists = userRepository.findPacklistsByUserId(id);
        return Response.ok().entity(userPacklists).build();
    }

    // note comment in update, reason for packlistsId
    @PUT
    @Path("{packlistId}")
    public void updatePackList(Packlist packlList) {
        packListRepository.update(packlList);
    }

    @DELETE
    @Path("{packlistId}")
    public void delete(@PathParam("packlistId") long packlistId) {
        packListRepository.delete(packlistId, Packlist.class);
    }



}
