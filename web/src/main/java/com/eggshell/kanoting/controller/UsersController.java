package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.authentication.PasswordHashes;
import com.eggshell.kanoting.controller.parent.BaseController;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.UserRepository;
import com.eggshell.kanoting.security.Roles;
import org.hibernate.validator.constraints.Email;

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

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users")
public class UsersController extends BaseController {

    private final URI resourceUri = URI.create("http://localhost:8080/nemo/resources/users");

    @Context
    ResourceContext rc;

    @Inject
    UserRepository userRepository;

    /*
        create
        get all
        get one by id
        get one by email
        get many by name
        update
        delete

        locator packlists
    */

    @GET
    public Response getAll() {
        List<User> users = userRepository.findAll();

        return Response.ok(users).build();
    }


    @GET
    @Path("/{userId}")
    @RolesAllowed({Roles.LOGGED_IN})
    public Response getUserById(@PathParam("userId") long id, @Context Request request) {
        Response.ResponseBuilder builder;
        User user = userRepository.findUserById(id);

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

    @GET
    @Path("by_email/{email}")
    public Response getUserByEmail(
            @Email @PathParam("email") String email) {
        User userByEmail = userRepository.findUserByEmail(email);

        return Response.ok(userByEmail).build();
    }

    @POST
    public Response create(@Valid User user, @Context UriInfo info) throws NoSuchAlgorithmException, InvalidKeySpecException {

        user.password = PasswordHashes.createHash(user.password);

        User persistedUser = userRepository.addUser(user);
        long id = persistedUser.id;

        URI uri = info.getAbsolutePathBuilder().
                path("/" + id).
                build();

        return Response.created(uri).link(resourceUri, "self").build();
    }

    @PUT
    @Path("{userId}")
    public Response update(@PathParam("userId") long userId, User user) {
        userRepository.updateUser(userId, user);

        return Response.noContent().build();
    }

    @DELETE
    @Path("{userId}")
    public Response deleteUser(@PathParam("userId") long userId) {
        userRepository.deleteUser(userId);
        return Response.ok().build();
    }


    /*
        Query param method by name
    */
    @GET
    @Path("search")
    public Response searchByUserName(@QueryParam("name") String name) {
        List<User> usersByName = userRepository.findUserByName(name);

        return Response.ok(usersByName).build();
    }
}
