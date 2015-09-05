package com.eggshell.kanoting.controller;


import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/usercontroller")
public class UserController {

    @Inject
    UserRepository userRepository;

    @GET
    @Path("/user/{userId}")
    public User getUser(@PathParam("userId") String userId) {
        return userRepository.findUser(Integer.parseInt(userId));
    }


}
