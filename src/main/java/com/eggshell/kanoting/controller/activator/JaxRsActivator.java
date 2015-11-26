package com.eggshell.kanoting.controller.activator;

import com.eggshell.kanoting.controller.controller.ItemController;
import com.eggshell.kanoting.controller.controller.PacklistController;
import com.eggshell.kanoting.controller.controller.UsersController;
import com.eggshell.kanoting.controller.controller.WishlistController;
import com.eggshell.kanoting.controller.mappers.EJBExceptionMapper;
import com.eggshell.kanoting.controller.mappers.WebApplicationExceptionMapper;
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
//        resources.save(RolesAllowedDynamicFeature.class);
//        resources.save(Authenticate.class);
        resources.add(EJBExceptionMapper.class);
        resources.add(WebApplicationExceptionMapper.class);

        return resources;
    }
}
