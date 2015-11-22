package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.controller.parent.BaseController;
import com.eggshell.kanoting.controller.subresources.PacklistUsersController;
import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.Packlist;
import com.eggshell.kanoting.repository.PackListRepository;
import com.eggshell.kanoting.repository.parent.BaseRepo;

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
    BaseRepo repo;
    @Inject
    PackListRepository packListRepository;

    @Context
    ResourceContext rc;

    /*
        create packlist
        get all
        get one by id
        update packlist
        delete packlist

    */

    /*
        base repo

        todo: refactor to UserPacklistCtrl? Packlist can not exist without an owner..
    */
    @POST
    public void addPackList(Packlist packlList) {
        repo.save(packlList);
    }

    /*
        base repo
    */
    @GET
    @SuppressWarnings("unchecked")
    public List<Packlist> getALL() {
        return repo.findAll(Packlist.class);
    }

    /*
        base repo
    */
    @GET
    @Path("/{packListId}")
    public Packlist getPackList(@PathParam("packListId") long packlistId) {
        return repo.find(Packlist.class, packlistId);
    }

    /*
        base repo
    */
    @PUT
    @Path("{packlistId}")
    public void updatePackList(Packlist packlList) {
        repo.update(packlList);
    }

    /*
        base repo
    */
    @DELETE
    @Path("{packlistId}")
    public void delete(@PathParam("packlistId") long packlistId) {
        repo.delete(Packlist.class, packlistId);
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

    /*
        locator method
    */
    @Path("users")
    public PacklistUsersController locatePacklistsUser(){
        return rc.getResource(PacklistUsersController.class);
    }

}
