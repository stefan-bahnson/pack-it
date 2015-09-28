package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.authentication.PasswordHashes;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.UserRepository;

import javax.inject.Inject;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Path("/users")
public class UserController {

    @Inject
    UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    public User getUser(@PathParam("userId") long id) {
        return userRepository.findUserById(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user) {

        try {
            user.password = PasswordHashes.createHash(user.password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        userRepository.addUser(user);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

}
