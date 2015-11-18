package com.eggshell.kanoting.controller.wishlist;

import com.eggshell.kanoting.controller.parent.BaseController;
import com.eggshell.kanoting.model.WishList;
import com.eggshell.kanoting.repository.WishListRepository;
import com.eggshell.kanoting.security.Roles;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RolesAllowed(Roles.LOGGED_IN)
@Path("/wishlists")
public class WishListController extends BaseController {

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
        wishListRepository.addWishList(loggedInUserId(), wishList);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateWishList(WishList wishList) {
        wishListRepository.updateWishList(loggedInUserId(), wishList);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteWishList(WishList wishList) {
        wishListRepository.deleteWishList(loggedInUserId(), wishList);
    }
}
