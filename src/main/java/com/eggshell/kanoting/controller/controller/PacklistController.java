package com.eggshell.kanoting.controller.controller;

import com.eggshell.kanoting.controller.controller.subresources.PacklistUsersController;
import com.eggshell.kanoting.model.model.Item;
import com.eggshell.kanoting.model.model.Packlist;
import com.eggshell.kanoting.model.repository.PackListRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/packlists")
public class PacklistController {

    @Inject
    PackListRepository packListRepository;

    @Context
    ResourceContext rc;

    /*
        get all
        get one by id
        update packlist
        delete packlist
    */

    @GET
    public List<Packlist> getALL() {
        return packListRepository.findAll(Packlist.class);
    }

    @GET
    @Path("/{packListId}")
    public Packlist getPackList(@PathParam("packListId") long packlistId) {
        return packListRepository.find(packlistId, Packlist.class);
    }

    /**
     * Removes an item from a packlist, if there are no relations to the item it also removes the item entity
     * todo: refactor to PacklistItemsCtrl and impl locator method?
     */
    @DELETE
    @Path("/{id}/items")
    public void deleteItemFromPackList(@PathParam("id") long id, List<Item> items) {
        packListRepository.deleteItemsFromPackList(id, items);
    }

    /*
        locator method
    */
    @Path("{packlistId}/users")
    public PacklistUsersController locatePacklistUsers(){
        return rc.getResource(PacklistUsersController.class);
    }
}
