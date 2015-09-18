package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.repository.ItemRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by tailage on 9/18/15.
 */

@Path("/items")
public class ItemController {

    @Inject
    ItemRepository itemRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{itemId}")
    public Item getUser(@PathParam("itemId") long id) {
        return itemRepository.findItemById(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addItem(Item item) {
        itemRepository.addItem(item);
    }
}
