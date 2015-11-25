package com.eggshell.kanoting.controller.subresources;


import com.eggshell.kanoting.model.WishList;
import com.eggshell.kanoting.repository.WishListRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserWishlistController {

    @Inject
    WishListRepository wishListRepository;

    @PUT
    @Path("{wishlistId}")
    public void updateWishList(@PathParam("wishlistId") long wishlistId, WishList wishList) {
        wishListRepository.update(wishList);
    }

    @DELETE
    @Path("{wishlistId}")
    public void deleteWishList(@PathParam("wishlistId") long wishlistId) {
        wishListRepository.delete(wishlistId, WishList.class);
    }
}
