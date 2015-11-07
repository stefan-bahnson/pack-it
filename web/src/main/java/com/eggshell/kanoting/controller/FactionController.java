package com.eggshell.kanoting.controller;


import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.FactionRepository;
import com.eggshell.kanoting.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/factions")
public class FactionController {

    @Inject
    UserRepository userRepository;

    @Inject
    FactionRepository factionRepository;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public Response addFactionToUser(User user) {
        factionRepository.addUserToFaction(user);
        return Response.ok().build();
    }
}
