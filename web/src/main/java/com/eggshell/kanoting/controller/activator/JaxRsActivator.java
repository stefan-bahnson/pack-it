package com.eggshell.kanoting.controller.activator;

import com.eggshell.kanoting.controller.user.UsersResource;
import com.eggshell.kanoting.controller.user.subresources.UserPacklistsResource;
import com.eggshell.kanoting.exception.mappers.EJBExceptionMapper;
import com.eggshell.kanoting.controller.*;
import com.eggshell.kanoting.exception.mappers.WebApplicationExceptionMapper;
import com.eggshell.kanoting.filter.Authenticate;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
@ApplicationPath("/")
public class JaxRsActivator extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new java.util.HashSet<>();

        resources.add(RootController.class);
        resources.add(UserPacklistsResource.class);

        resources.add(UsersResource.class);
        resources.add(RoleController.class);
        resources.add(ItemController.class);
        resources.add(PackListController.class);
        resources.add(WishListController.class);
        resources.add(LoginController.class);

        // For activation of javax.security.annotations
        resources.add(RolesAllowedDynamicFeature.class);
        resources.add(Authenticate.class);
        resources.add(EJBExceptionMapper.class);
        resources.add(WebApplicationExceptionMapper.class);

        return resources;
    }
}
