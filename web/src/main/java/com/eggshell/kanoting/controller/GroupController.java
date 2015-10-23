package com.eggshell.kanoting.controller;

import com.eggshell.kanoting.filter.helper.annotation.Role;
import com.eggshell.kanoting.filter.helper.annotation.Secured;
import com.eggshell.kanoting.model.Group;
import com.eggshell.kanoting.repository.GroupRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Secured(Role.LOGGED_IN)
@Path("/groups")
public class GroupController {

    @Inject
    GroupRepository groupRepository;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addGroup(Group group) {
        groupRepository.addGroup(group);
    }

}
