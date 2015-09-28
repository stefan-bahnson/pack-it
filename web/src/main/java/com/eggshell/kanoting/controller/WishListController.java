package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.model.WishList;
import com.eggshell.kanoting.repository.WishListRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by tailage on 9/19/15.
 */

@Path("/wishlists")
public class WishListController {

    @Inject
    WishListRepository wishListRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{wishListId}")
    public WishList getWishList(@PathParam("wishListId") long id) {
        return wishListRepository.findWishListById(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addWishList(WishList wishList) {
        wishListRepository.addWishList(wishList);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateWishList(WishList wishList) {
        wishListRepository.updateWishList(wishList);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteWishList(WishList wishList) {
        wishListRepository.deleteWishList(wishList);
    }
}