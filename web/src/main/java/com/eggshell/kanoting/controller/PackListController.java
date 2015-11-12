package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.controller.parent.BaseController;
import com.eggshell.kanoting.filter.helper.annotation.Role;
import com.eggshell.kanoting.filter.helper.annotation.Secured;
import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.repository.PackListRepository;
import com.eggshell.kanoting.security.Roles;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
@RolesAllowed(Roles.LOGGED_IN)
public class PackListController extends BaseController {

    @Inject
    PackListRepository packListRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{packListId}")
    public PackList getPackList(@PathParam("packListId") long id) {
        return packListRepository.findPackListById(id, loggedInUserId());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPackList(PackList packList) {
        packListRepository.addPackList(packList);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePackList(PackList packList) {
        packListRepository.updatePackList(loggedInUserId(), packList);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletePackList(PackList packList) {
        packListRepository.deletePacklist(loggedInUserId(), packList);
    }

    /**
     * Removes an item from a packlist, if there are no relations to the item it also removes the item entity
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}/items")
    public void deleteItemFromPackList(@PathParam("id") long id, List<Item> items) {
        packListRepository.deleteItemsFromPackList(id, loggedInUserId(), items);
    }
}
