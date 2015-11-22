package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.controller.parent.BaseController;
import com.eggshell.kanoting.controller.subresources.PacklistUsersController;
import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.repository.PackListRepository;
import com.eggshell.kanoting.repository.UserRepository;
import com.eggshell.kanoting.repository.parent.BaseRepository;
import com.eggshell.kanoting.security.Roles;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/packlists")
public class PackListController extends BaseController {

    @Inject
    PackListRepository packListRepository;
    @Inject
    BaseRepository repo;
    @Inject
    UserRepository userRepository;
    @Context
    ResourceContext rc;

    /*
        create packlist
        get all
        get one by id
        update packlist
        delete packlist

    */

    @GET
    public List<PackList> getALL() {
        return packListRepository.findAll();
    }

    @GET
    @Path("/{packListId}")
    public PackList getPackList(@PathParam("packListId") long packlistId) {
        return packListRepository.findPackListById(packlistId);
    }

    // todo: refactor to UserPacklistCtrl? Packlist can not exist without an owner..
    @POST
    public void addPackList(PackList packList) {
        repo.add(packList);
    }

    @PUT
    @Path("{packlistId}")
    public void updatePackList(PackList packList) {
        packListRepository.updatePackList(packList);
    }

    @DELETE
    @Path("{packlistId}")
    public void delete(@PathParam("packlistId") long packlistId) {
        packListRepository.deletePacklist(packlistId);
    }

    /**
     * Removes an item from a packlist, if there are no relations to the item it also removes the item entity
     * todo: refactor to PacklistItemsCtrl and impl locator method?
     * §
     */
    @DELETE
    @Path("/{id}/items")
    public void deleteItemFromPackList(@PathParam("id") long id, List<Item> items) {
        packListRepository.deleteItemsFromPackList(id, items);
    }

    /*
        locator methods
    */

    @Path("{packlistId}/users")
    public PacklistUsersController locatePacklistUsers(){
        return rc.getResource(PacklistUsersController.class);
    }

    @Path("users")
    public PacklistUsersController locatePacklistsUser(){
        return rc.getResource(PacklistUsersController.class);
    }

}
