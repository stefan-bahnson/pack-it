package com.eggshell.kanoting.controller.controllers.sub;



import com.eggshell.kanoting.model.entity.WishList;
import com.eggshell.kanoting.model.repository.WishListRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserWishlistController {

    @Inject
    WishListRepository wishListRepository;

    @POST
    public Response create(@PathParam("userId") long userId, WishList wishlist) {
        wishListRepository.addWishlistToUser(userId, wishlist);

        return Response.status(Response.Status.CREATED).build();
    }

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
