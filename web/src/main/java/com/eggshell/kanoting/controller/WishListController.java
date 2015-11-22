package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.controller.parent.BaseController;
import com.eggshell.kanoting.model.WishList;
import com.eggshell.kanoting.repository.WishListRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/wishlists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WishListController extends BaseController {

    @Inject
    WishListRepository wishListRepository;

    /*
        create
        get all
        get one by id
        update
        delete
    */

    @POST
    public void create(WishList wishList) {
        wishListRepository.addWishList(wishList);
    }

    @GET
    public Response getAll() {
        List<WishList> wishlists = wishListRepository.findAll();

        return Response.ok(wishlists).build();
    }

    @GET
    @Path("/{wishListId}")
    public WishList getWishList(@PathParam("wishListId") long id) {
        return wishListRepository.findWishListById(id);
    }


    /*
        todo: in this case we are updating a specifik entity on collection level. see PackListController for alternative.
    */
    @PUT
    public void updateWishList(WishList wishList) {
        wishListRepository.updateWishList(wishList);
    }

    /*
        same note as for method above
    */
    @DELETE
    public void deleteWishList(WishList wishList) {
        wishListRepository.deleteWishList(wishList);
    }
}
