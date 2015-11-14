package com.eggshell.kanoting.controller.user.subresources;

import com.eggshell.kanoting.controller.parent.BaseController;
import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.repository.PackListRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserPacklistsResource extends BaseController{

    @Inject
    PackListRepository packListRepository;

    @GET
    public List<PackList> getAllUserPackLists(@PathParam("userId") long userId) {
        return packListRepository.findPackListsByUserId(userId);
    }
}
