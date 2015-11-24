package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.controller.parent.BaseController;
import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.repository.ItemRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemController extends BaseController{

    private final URI resourceUri = URI.create("http://localhost:8080/nemo/resources/items");

    @Inject
    ItemRepository itemRepository;

    /*
        create
        get all
        get one by id
        update
        delete
    */


    @POST
    public Response addItem(@Valid Item item, @Context UriInfo info) {

        Item persistedItem =  itemRepository.add(item);
        long id = persistedItem.id;

        URI uri = info.getAbsolutePathBuilder()
                .path("/" + id)
                .build();

        return Response.created(uri).link(resourceUri, "self").build();
    }


    @GET
    @SuppressWarnings("unchecked")
    public Response getAll() {
        List<Item> items = itemRepository.findAll(Item.class);

        return Response.ok(items).build();
    }

    @GET
    @Path("/{itemId}")
    public Response getItem(@PathParam("itemId") long id) {
        Item item = itemRepository.find(id, Item.class);

        Response response;

        // fixme: mapper for this?
        if(item == null) {
            response = Response.noContent().build();
        } else {
            response = Response.ok().entity(item).build();
        }

        return response;
    }

    // fixme: creates new Item when updating
    @PUT
    @Path("{itemId}")
    public Response update(@PathParam("itemId") long itemId, Item item) {
        itemRepository.update(item);

        return Response.noContent().build();
    }

    /**
     * Only admin shall have access to this method
     * When executed, it shall remove an item entity and all relations to it
     */
    @DELETE
    @Path("{itemId}")
    public Response deleteItem(@PathParam("itemId") long itemId) {
        itemRepository.delete(itemId, Item.class);
        return Response.ok().build();
    }
}
