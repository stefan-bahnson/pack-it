package com.eggshell.kanoting.controller;


import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.RoleRepository;
import com.eggshell.kanoting.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/factions")
public class RoleController {

    @Inject
    UserRepository userRepository;

    @Inject
    RoleRepository roleRepository;

    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public Response addRoleToUser(User user) {
        roleRepository.addUserToFaction(user);
        return Response.ok().build();
    }
}
