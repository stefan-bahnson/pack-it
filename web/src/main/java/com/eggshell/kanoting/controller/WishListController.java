package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.filter.helper.annotation.Role;
import com.eggshell.kanoting.filter.helper.annotation.Secured;
import com.eggshell.kanoting.model.WishList;
import com.eggshell.kanoting.repository.WishListRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Secured(Role.LOGGED_IN)
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addWishList(WishList wishList) {
        wishListRepository.addWishList(wishList);
    }

    @PUT
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
