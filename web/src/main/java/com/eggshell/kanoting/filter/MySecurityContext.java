package com.eggshell.kanoting.filter;


import com.eggshell.kanoting.model.Role;
import com.eggshell.kanoting.model.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext {

    private final User user;

    public MySecurityContext(User user) {
        this.user = user;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public boolean isUserInRole(String role) {

        boolean isRole = false;

        // this user has this role?
        for (Role r : user.roles) {
            if (r.name.equals(role)) {
                isRole = true;
            }
        }

        return isRole;

    }
}
