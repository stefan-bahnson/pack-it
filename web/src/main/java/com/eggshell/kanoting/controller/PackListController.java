package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.filter.helper.annotation.Role;
import com.eggshell.kanoting.filter.helper.annotation.Secured;
import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.repository.PackListRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Secured(Role.LOGGED_IN)
@Path("/packlists")
public class PackListController {

    @Inject
    PackListRepository packListRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{packListId}")
    public PackList getPackList(@PathParam("packListId") long id) {
        return packListRepository.findPackListById(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPackList(PackList packList) {
        packListRepository.addPackList(packList);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePackList(PackList packList) {
        packListRepository.updatePackList(packList);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletePackList(PackList packList){
        packListRepository.deletePacklist(packList);
    }
}
