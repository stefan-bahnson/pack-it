package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.controller.parent.BaseController;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.UserRepository;
import com.eggshell.kanoting.repository.parent.BaseRepo;
import com.eggshell.kanoting.security.Roles;
import org.hibernate.validator.constraints.Email;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @POST
    public Response create(@Valid User user, @Context UriInfo info) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        no Hashing to make update work for the moment.
//        user.password = PasswordHashes.createHash(user.password);

        userRepository.add(user);

        return Response.status(Response.Status.CREATED).build();
    }

    /*
        Create by form params. Checks for correct fields and not null
        for correct mapping to User obj.
    */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createbyForm(
            @NotNull(message = "Name must be provided")
            @FormParam("name") String name,
            @NotNull(message = "Email must be provided")
            @Email(message = "Not a valid email! Format expected: example@mail.com") // should the API care about email format?
            @FormParam("email") String email,
            @NotNull(message = "Password must be provided")
            @FormParam("password") String password)
    {
        userRepository.add(new User(name, email, password));

        return Response.status(Response.Status.CREATED).build();
    }

    /*
        base repo
    */
    @GET
    @SuppressWarnings("unchecked")
    public Response getAll() {
        List<User> users = userRepository.findAll(User.class);
        return Response.ok(users).build();
    }

    /*
        base repo
    */
    @GET
    @Path("/{userId}")
    @RolesAllowed({Roles.LOGGED_IN})
    public Response getUserById(@PathParam("userId") long id, @Context Request request) {
        Response.ResponseBuilder builder;
        User user = userRepository.find(id, User.class);

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
    public Response getUserByEmail(@Email @PathParam("email") String email) {
        User userByEmail = userRepository.findUserByEmail(email);

        return Response.ok(userByEmail).build();
    }

    @PUT
    @Path("{userId}")
    public Response update(@PathParam("userId") long userId, User user) {

        userRepository.update(user);

        return Response.noContent().build();
    }

    @PUT
    @Path("{userId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateByForm(
            @PathParam("userId") long userId,
            @FormParam("name") String name,
            @FormParam("email") String email,
            @FormParam("password") String password)
    {
        userRepository.updateUserByForm(userId, name, email, password);
    }

    @DELETE
    @Path("{userId}")
    public Response deleteUser(@PathParam("userId") long userId) {
        userRepository.delete(userId, User.class);
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
