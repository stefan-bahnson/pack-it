package com.eggshell.kanoting.controller;

import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;

/**
 * Write Javadoc...
 */
@Path(("/"))
public class RootController {

    @Context
    ResourceContext rc;

    /*
        We need locator methods for all main resources
        that are used in other contexts as a sub-resource,
        since they do not have a class path specified.
    */
    @Path("/packlists")
    public PackListController locatePacklists() {
        return rc.getResource(PackListController.class);
    }

    /*
        we do not need a locator method for users since
        the path will always start with /users
        i.e users/1/packlists
    */

}
