package com.eggshell.kanoting;

import com.eggshell.kanoting.controller.controllers.ItemController;
import com.eggshell.kanoting.controller.controllers.PacklistController;
import com.eggshell.kanoting.controller.controllers.UsersController;
import com.eggshell.kanoting.controller.controllers.WishlistController;
import com.eggshell.kanoting.controller.mappers.EJBExceptionMapper;
import com.eggshell.kanoting.model.exceptions.mappers.WebApplicationExceptionMapper;
import com.eggshell.kanoting.model.repository.parent.Repository;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
@ApplicationPath("/")
public class JaxRsActivator extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new java.util.HashSet<>();

        resources.add(Repository.class);

        resources.add(UsersController.class);
        resources.add(ItemController.class);
        resources.add(PacklistController.class);
        resources.add(WishlistController.class);

        // For activation of javax.security.annotations
//        controllers.save(RolesAllowedDynamicFeature.class);
//        controllers.save(Authenticate.class);
        resources.add(EJBExceptionMapper.class);
        resources.add(WebApplicationExceptionMapper.class);

        return resources;
    }
}
