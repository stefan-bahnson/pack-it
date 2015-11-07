package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.authentication.PasswordHashes;
import com.eggshell.kanoting.controller.parent.BaseController;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.UserRepository;
import com.eggshell.kanoting.security.Roles;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import javax.validation.Valid;
import javax.ws.rs.*;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Path("/users")
public class UserController extends BaseController {

    private final URI resourceUri = URI.create("http://localhost:8080/nemo/resources/users");

    @Inject
    UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    @RolesAllowed({Roles.LOGGED_IN})
    public Response getUser(@PathParam("userId") long id) {
        User user = userRepository.findUserById(id, loggedInUserId());
        Response response;
        if(user == null) {
           response = Response.noContent().header("cause: ", "No entity found for: " + id).build();
        } else {
            response = Response.ok().entity(user).build();
        }
        return response;
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

}
