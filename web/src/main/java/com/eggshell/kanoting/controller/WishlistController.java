package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.model.WishList;
import com.eggshell.kanoting.repository.WishListRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/wishlists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WishlistController {

    @Inject
    WishListRepository wishListRepository;

    @Context
    ResourceContext rc;


    /*
        create
        get all
        get one by id
        update
        delete
    */

    @POST
    public void create(WishList wishList) {
        wishListRepository.add(wishList);
    }

    @GET
    public Response getAll() {
        List<WishList> wishlists = wishListRepository.findAll(WishList.class);

        return Response.ok(wishlists).build();
    }

    @GET
    @Path("/{wishlistId}")
    public WishList getWishList(@PathParam("wishlistId") long id) {
        return wishListRepository.find(id, WishList.class);
    }

}
