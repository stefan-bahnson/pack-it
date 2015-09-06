package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.data.User;
import com.eggshell.kanoting.manager.UserManager;

import javax.inject.Inject;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserController {

    @Inject
    UserManager userManager;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{userId}")
    public User getUser(@PathParam("userId") long id) {
        return userManager.getUserById(id);
    }

    @PUT
    public void addUser(User user) {
        userManager.addUser(user);
    }


}
