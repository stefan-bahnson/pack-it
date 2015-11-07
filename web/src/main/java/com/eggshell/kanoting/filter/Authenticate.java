package com.eggshell.kanoting.filter;

import com.eggshell.kanoting.model.Role;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.UserRepository;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class Authenticate implements ContainerRequestFilter {

    @Inject
    UserRepository userRepository;

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
//        String authorizationHeader = crc.getHeaderString(HttpHeaders.AUTHORIZATION);
//        if(authorizationHeader == null) {
//            crc.abortWith(Response.status(Response.Status.FORBIDDEN).build());
//            return;
//        }
        System.out.println("hej");
        User user = new User();
        user.roles = new HashSet<>();
        user.roles.add(new Role("admin"));
        crc.setSecurityContext(new MySecurityContext(user));
//        BasicAuthorization basicAuth = new BasicAuthorization(authorizationHeader);
//        User user = userRepository.findUserByEmail(basicAuth.username());
//        boolean authenticated = userRepository.authenticate(user, basicAuth.password());
//        if(!authenticated) {
//            crc.abortWith(Response.status(Response.Status.FORBIDDEN).build());
//        } else {
//            crc.setProperty(User.class.getName(), user);
//        }
    }
}