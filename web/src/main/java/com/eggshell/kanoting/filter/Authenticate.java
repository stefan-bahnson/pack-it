package com.eggshell.kanoting.filter;

import com.eggshell.kanoting.filter.helper.annotation.Secured;
import com.eggshell.kanoting.filter.helper.BasicAuthorization;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.UserRepository;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class Authenticate implements ContainerRequestFilter {

    @Inject
    UserRepository userRepository;

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        String authorizationHeader = crc.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader == null) {
            crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
        BasicAuthorization basicAuth = new BasicAuthorization(authorizationHeader);
        User user = userRepository.findUserByEmail(basicAuth.getUsername());
        boolean authenticated = userRepository.authenticate(user, basicAuth.getHashedPassword());
        if(!authenticated) {
            crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        } else {
            crc.setProperty(User.class.getName(), user);
        }
    }
}