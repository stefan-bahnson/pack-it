package com.eggshell.kanoting.activator;

import com.eggshell.kanoting.controller.ItemController;
import com.eggshell.kanoting.controller.PackListController;
import com.eggshell.kanoting.controller.RoleController;
import com.eggshell.kanoting.controller.RootController;
import com.eggshell.kanoting.controller.UsersController;
import com.eggshell.kanoting.controller.WishListController;
import com.eggshell.kanoting.exception.mappers.EJBExceptionMapper;
import com.eggshell.kanoting.exception.mappers.WebApplicationExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
@ApplicationPath("/")
public class JaxRsActivator extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new java.util.HashSet<>();

        resources.add(RootController.class);

        resources.add(UsersController.class);
        resources.add(RoleController.class);
        resources.add(ItemController.class);
        resources.add(PackListController.class);
        resources.add(WishListController.class);
//        resources.save(LoginController.class);

        // For activation of javax.security.annotations
//        resources.save(RolesAllowedDynamicFeature.class);
//        resources.save(Authenticate.class);
        resources.add(EJBExceptionMapper.class);
        resources.add(WebApplicationExceptionMapper.class);

        return resources;
    }
}
