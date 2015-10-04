package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.filter.helper.annotation.Role;
import com.eggshell.kanoting.filter.helper.annotation.Secured;
import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.repository.ItemRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Secured(Role.LOGGED_IN)
@Path("/items")
public class ItemController {

    @Inject
    ItemRepository itemRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{itemId}")
    public Item getItem(@PathParam("itemId") long id) {
        return itemRepository.findItemById(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addItem(Item item) {
        itemRepository.addItem(item);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteItem(Item item) {
        itemRepository.deleteItem(item);
    }
}
