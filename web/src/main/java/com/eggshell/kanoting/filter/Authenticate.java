package com.eggshell.kanoting.filter;

import com.eggshell.kanoting.filter.helper.BasicAuthorization;
import com.eggshell.kanoting.filter.helper.annotation.Secured;
import com.eggshell.kanoting.model.Role;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.UserRepository;
import com.eggshell.kanoting.security.Roles;

import javax.annotation.Priority;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@RolesAllowed({Roles.LOGGED_IN})
@Provider
@Priority(Priorities.AUTHENTICATION)
public class Authenticate implements ContainerRequestFilter {

    @Inject
    UserRepository userRepository;

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        String authorizationHeader = crc.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader == null) {
            crc.setSecurityContext(new MySecurityContext());
            return;
        }
        BasicAuthorization basicAuth = new BasicAuthorization(authorizationHeader);
        User user = null;
        boolean authenticated = false;
        try {
            System.out.println(basicAuth.username());
            user = userRepository.findUserByEmail(basicAuth.username());
            authenticated = userRepository.authenticate(user, basicAuth.password());
        } catch(NoResultException|EJBException e) {}
        if(authenticated) {
            crc.setProperty(User.class.getName(), user);
            crc.setSecurityContext(new MySecurityContext(user));
        }
    }
}