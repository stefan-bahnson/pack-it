package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.authentication.PasswordHashes;
import com.eggshell.kanoting.filter.helper.annotation.Role;
import com.eggshell.kanoting.filter.helper.annotation.Secured;
import com.eggshell.kanoting.model.Group;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.GroupRepository;
import com.eggshell.kanoting.repository.UserRepository;

import javax.inject.Inject;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

@Path("/users")
public class UserController {

    @Inject
    UserRepository userRepository;

    @Inject
    GroupRepository groupRepository;

    @Secured(Role.LOGGED_IN)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    public User getUser(@PathParam("userId") long id) {
        return userRepository.findUserById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user) {
        try {
            user.password = PasswordHashes.createHash(user.password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        user.roles = new ArrayList<>();
        user.roles.add(groupRepository.findByName("user"));
        userRepository.addUser(user);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(User user) {

        User userToMerge;

        userToMerge = userRepository.findUserById(user.id);
        userToMerge.roles = user.roles;

        userRepository.updateUser(userToMerge);
    }

    private merge(User newUser, User existingUser) {
        
    }


    @Secured(Role.LOGGED_IN)
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

}
