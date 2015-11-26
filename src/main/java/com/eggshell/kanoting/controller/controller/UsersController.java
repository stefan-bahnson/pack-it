package com.eggshell.kanoting.controller.controller;

import com.eggshell.kanoting.controller.controller.subresources.UserPacklistController;
import com.eggshell.kanoting.controller.controller.subresources.UserWishlistController;
import com.eggshell.kanoting.model.model.User;
import com.eggshell.kanoting.model.repository.UserRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.*;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users")
public class UsersController {

    private final URI resourceUri = URI.create("http://localhost:8080/nemo/resources/users");

    @Context
    ResourceContext rc;

    @Inject
    UserRepository userRepository;

    /*
        create
        get all
        get one by id
        update
        delete
    */

    @POST
    public Response create(@Valid User user, @Context UriInfo info) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        no Hashing to make update work for the moment.
//        user.password = PasswordHashes.createHash(user.password);

        userRepository.add(user);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response getAll() {
        List<User> users = userRepository.findAll(User.class);

        return Response.ok(users).build();
    }

    @GET
    @Path("/{userId}")
    public Response getUserById(@PathParam("userId") long id, @Context Request request) {
        User user = userRepository.find(id, User.class);

        return Response.ok(user).build();
    }

    @PUT
    @Path("{userId}")
    public Response update(@PathParam("userId") long userId, User user) {
        userRepository.update(user);

        return Response.noContent().build();
    }

    @DELETE
    @Path("{userId}")
    public Response deleteUser(@PathParam("userId") long userId) {
        userRepository.delete(userId, User.class);

        return Response.ok().build();
    }

    @Path("{userId}/packlists")
    public UserPacklistController locateUserPacklists() {
        return rc.getResource(UserPacklistController.class);
    }

    @Path("{userId}/wishlists")
    public UserWishlistController locateUserWishlists() {
        return rc.getResource(UserWishlistController.class);
    }
}
