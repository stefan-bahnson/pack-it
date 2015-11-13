package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.controller.parent.BaseController;
import com.eggshell.kanoting.filter.helper.annotation.Role;
import com.eggshell.kanoting.filter.helper.annotation.Secured;
import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.PackListRepository;
import com.eggshell.kanoting.repository.UserRepository;
import com.eggshell.kanoting.security.Roles;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RolesAllowed(Roles.LOGGED_IN)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PackListController extends BaseController {

    @Inject
    PackListRepository packListRepository;
    @Inject
    UserRepository userRepository;

    @GET
    public List<PackList> getALL() {
        return packListRepository.findAll();
    }

    @GET
    @Path("/{packListId}")
    public PackList getPackList(@PathParam("packListId") long id) {
        return packListRepository.findPackListById(id, loggedInUserId());
    }

    @POST
    public void addPackList(PackList packList) {
        packListRepository.addPackList(packList);
    }

    @PUT
    public void updatePackList(PackList packList) {
        packListRepository.updatePackList(loggedInUserId(), packList);
    }

    @DELETE
    public void deletePackList(PackList packList) {
        packListRepository.deletePacklist(loggedInUserId(), packList);
    }

    /**
     * Removes an item from a packlist, if there are no relations to the item it also removes the item entity
     */
    @DELETE
    @Path("/{id}/items")
    public void deleteItemFromPackList(@PathParam("id") long id, List<Item> items) {
        packListRepository.deleteItemsFromPackList(id, loggedInUserId(), items);
    }

    /*
        User related resources
    */

    @GET
    public List<PackList> getAllUserPackLists(@PathParam("userId") long userId) {
        return packListRepository.findPackListsByUserId(userId);
    }
}
