package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.authentication.PasswordHashes;
import com.eggshell.kanoting.controller.parent.BaseController;
import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.PackListRepository;
import com.eggshell.kanoting.repository.UserRepository;
import com.eggshell.kanoting.security.Roles;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import javax.validation.Valid;
import javax.ws.rs.*;

import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.*;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;


@Path("/users")
public class UserController extends BaseController {

    private final URI resourceUri = URI.create("http://localhost:8080/nemo/resources/users");

    @Inject
    UserRepository userRepository;

    @Inject
    PackListRepository packListRepository;

    @Context
    ResourceContext rc;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    @RolesAllowed({Roles.LOGGED_IN})
    public Response getUser(@PathParam("userId") long id, @Context Request request) {
        Response.ResponseBuilder builder;
        User user = userRepository.findUserById(id, loggedInUserId());

        // Set up cache properties
        CacheControl cc = new CacheControl();
        cc.setMaxAge(86400);
        EntityTag eTag = new EntityTag(Integer.toString(user.hashCode()));
        builder = request.evaluatePreconditions(eTag);


        if(builder == null)  {
            builder = Response.ok().entity(user).tag(eTag);
        } else {
            System.out.println("failed");
        }

        builder.cacheControl(cc);
        return builder.build();
    }

    /*
     * Is not secure atm
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}/packlists")
    public Response getPacklistsOfUser(@PathParam("userId") long userId) {
        List<PackList> packLists = packListRepository.findPackListsByUser(userId);
        return Response.ok().entity(packLists).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(@Valid User user, @Context UriInfo info) throws NoSuchAlgorithmException, InvalidKeySpecException {

        user.password = PasswordHashes.createHash(user.password);

        User persistedUser = userRepository.addUser(user);
        long id = persistedUser.id;

        URI uri = info.getAbsolutePathBuilder().
                path("/" + id).
                build();

        return Response.created(uri).link(resourceUri, "self").build();
    }


    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"logged_in"})
    public Response deleteUser(User user) {
        userRepository.deleteUser(loggedInUserId(), user);
        return Response.ok().build();
    }


    /* -------------*/
    /*              */
    /*  Not needed  */
    /*              */
    /* -------------*/

//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void updateUser(User user) {
//        userRepository.updateUser(user);
//    }

    /*
        PackLists sub-resource specific actions.
        Can be refactored to UserPacklistsCtrl if more than one entrypoint i.e.

            /packlists
            /users/user/packlists

        else PacklistCtrl

            /users/user/packlists

    */

    @GET
    @Path("{userId}/packlists")
    public List<PackList> getPacklistsByUserId(@PathParam("userId") long userId) {
        return packListRepository.findPackListsByUser(userId);
    }
    @GET
    @Path("{userId}/packlists/{packListId}")
    public PackList getPacklistById(@PathParam("userId") long userId,
                                           @PathParam("packListId") long packListId) {
        return packListRepository.findPackListById(packListId, userId);
    }

    @POST
    @Path("{userId}/packlists/{packListId}")
    public void addUserToPackList(@PathParam("userId") long userId,
                                  @PathParam("packListId") long packListId) {
        // add user to packlist
    }

    @DELETE
    @Path("{userId}/packlists/{packListId}")
    public void removeUserFromPackList(@PathParam("userId") long userId,
                                  @PathParam("packListId") long packListId) {
        // remove user from packlist
    }

    /*
        Addresses sub-resource specific actions
        Can be refactored to UserAddressesCtrl if more than one entrypoint i.e.

            /addresses
            /users/user/addresses

        else AddressesCtrl

            /users/user/addresses
    */

    @GET
    @Path("{userId}/adresses")
    public void getAdressesByUserId(@PathParam("userId") long userId) {
        // find addresses by user id
    }
    @GET
    @Path("{userId}/adresses/{adressId}")
    public void getAdressByUserId(@PathParam("userId") long userId,
                                  @PathParam("adresstId") long adressId) {
        // find address by user id and address id
    }

    @POST
    @Path("{userId}/adresses/{adresstId}")
    public void addAddressToUser(@PathParam("userId") long userId,
                                  @PathParam("adresstId") long adresstId) {
        // add user to packlist
    }

    @DELETE
    @Path("{userId}/adresses/{adresstId}")
    public void removeAddressFromUser(@PathParam("userId") long userId,
                                       @PathParam("adresstId") long adresstId) {
        // remove user from packlist
    }
}
