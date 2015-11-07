package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.filter.helper.annotation.Role;
import com.eggshell.kanoting.filter.helper.annotation.Secured;
import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.ItemRepository;

import javax.inject.Inject;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Secured(Role.LOGGED_IN)
@Path("/items")
public class ItemController {

    private final URI resourceUri = URI.create("http://localhost:8080/nemo/resources/items");

    @Inject
    ItemRepository itemRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{itemId}")
    public Response getItem(@PathParam("itemId") long id) {
        Item item = itemRepository.findItemById(id);

        Response response;

        if(item == null) {
            response = Response.noContent().build();
        } else {
            response = Response.ok().entity(item).build();
        }

        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(@Valid Item item, @Context UriInfo info) {

        Item persistedItem =  itemRepository.addItem(item);
        long id = persistedItem.id;

        URI uri = info.getAbsolutePathBuilder().
                path("/" + id).
                build();

        return Response.created(uri).link(resourceUri, "self").build();
    }

    /**
     * Only admin shall have access to this method
     * When executed, it shall remove an item entity and all relations to it
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteItem(Item item) {
        itemRepository.deleteItem(item);
        return Response.ok().build();
    }
}
